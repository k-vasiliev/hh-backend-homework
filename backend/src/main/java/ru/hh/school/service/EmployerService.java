package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.util.EmployerMapper;
import ru.hh.school.util.IdParameterValidator;
import ru.hh.school.util.PaginationValidator;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerService {

    private final EmployerDao employerDao;
    private final EmployerMapper employerMapper;
    private final ApiService apiService;
    private final CounterService counterService;
    private final CommentService commentService;
    private final PaginationValidator paginationValidator;
    private final IdParameterValidator idParameterValidator;

    public EmployerService(EmployerDao employerDao, EmployerMapper employerMapper, ApiService apiService, CounterService counterService, CommentService commentService, PaginationValidator paginationValidator, IdParameterValidator idParameterValidator) {
        this.employerDao = employerDao;
        this.employerMapper = employerMapper;
        this.apiService = apiService;
        this.counterService = counterService;
        this.commentService = commentService;
        this.paginationValidator = paginationValidator;
        this.idParameterValidator = idParameterValidator;
    }

    public Employer getEmployer(Integer id) {
        return employerDao.get(Employer.class, id).orElseThrow(NotFoundException::new);
    }

    public List<FavoriteEmployerDto> getFavorites(Integer page, Integer perPage) {
        paginationValidator.validate(page, perPage);
        List<Employer> employers = employerDao.getFavoritesWithPagination(page, perPage);
        //TODO update counter in separate thread?
        employers.stream()
                .map(employer -> employer.getId())
                .forEach(counterService::incrementEmployerCounter);
        return employers.stream().map(employerMapper::mapDataFromDatabase).collect(Collectors.toList());
    }

    public Employer addEmployerToFavorites(Integer employerId, String comment) {
        try {
            getEmployer(employerId);
            throw new BadRequestException("Bad request");
        } catch (NotFoundException e) {
            return addNewEmployerToFavorites(employerId, comment);
        }
    }

    public Employer addNewEmployerToFavorites(Integer employerId, String comment) {
        String dataFromApi = apiService.fetchEmployersFromApiById(employerId);
        EmployerDtoById employerDto = employerMapper.mapDataFromApiById(dataFromApi);
        Employer employer = employerMapper.mapEmployerDtoToEntity(employerDto, comment);
        employerDao.save(employer);
        return employer;
    }

    @Transactional
    public void updateComment(Integer employerId, String comment) {
        idParameterValidator.validate(employerId);
        commentService.updateComment(employerId, comment);
    }

    @Transactional
    public void deleteCompany(Integer employerId) {
        idParameterValidator.validate(employerId);
        Employer employer = employerDao.getWithPessimisticLocking(employerId).orElseThrow(NotFoundException::new);
        employerDao.delete(employer);
    }

    @Transactional
    public Employer refresh(Integer employerId) {
        idParameterValidator.validate(employerId);
        Employer employer = getEmployer(employerId);
        String dataFromApi = apiService.fetchEmployersFromApiById(employerId);
        EmployerDtoById employerDto = employerMapper.mapDataFromApiById(dataFromApi);
        return employerMapper.refreshEmployer(employer, employerDto);
    }

}
