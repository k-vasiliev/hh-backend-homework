package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.util.EmployerMapper;
import ru.hh.school.util.IdParameterValidator;
import ru.hh.school.util.PaginationValidator;
import ru.hh.school.util.StringParameterFilter;

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
    private final StringParameterFilter stringParameterFilter;
    private final PaginationValidator paginationValidator;
    private final IdParameterValidator idParameterValidator;

    public EmployerService(EmployerDao employerDao, EmployerMapper employerMapper, ApiService apiService, StringParameterFilter stringParameterFilter, PaginationValidator paginationValidator, IdParameterValidator idParameterValidator) {
        this.employerDao = employerDao;
        this.employerMapper = employerMapper;
        this.apiService = apiService;
        this.stringParameterFilter = stringParameterFilter;
        this.paginationValidator = paginationValidator;
        this.idParameterValidator = idParameterValidator;
    }

    private Employer getEmployer(Integer id) {
        return employerDao.get(Employer.class, id).orElseThrow(NotFoundException::new);
    }

    public List<FavoriteEmployerDto> getFavorites(Integer page, Integer perPage) {
        paginationValidator.validate(page, perPage);
        List<Employer> employers = employerDao.getFavoritesWithPagination(page, perPage);
        return employers.stream().map(employerMapper::mapDataFromDatabase).collect(Collectors.toList());
    }

    public Employer addEmployerToFavorites(Integer employerId, String comment) {
        try {
            getEmployer(employerId);
            throw new BadRequestException();
        } catch (NotFoundException e) {
            String validComment = stringParameterFilter.filter(comment);
            EmployerDtoById employerDto = apiService.fetchEmployersFromApiById(employerId);
            Employer employer = employerMapper.mapEmployerDtoToEntity(employerDto, validComment);
            employerDao.save(employer);
            return employer;
        }
    }

    @Transactional
    public Employer updateComment(Integer employerId, String comment) {
        idParameterValidator.validate(employerId);
        String validComment = stringParameterFilter.filter(comment);
        Employer employer = getEmployer(employerId);
        employer.setComment(validComment);
        return employer;
    }

    @Transactional
    public void deleteCompany(Integer employerId) {
        idParameterValidator.validate(employerId);
        Employer employer = getEmployer(employerId);
        employerDao.delete(employer);
    }

    @Transactional
    public Employer refresh(Integer employerId) {
        idParameterValidator.validate(employerId);
        Employer employer = getEmployer(employerId);
        EmployerDtoById employerDto = apiService.fetchEmployersFromApiById(employerId);
        return employerMapper.refreshEmployer(employer, employerDto);
    }

}
