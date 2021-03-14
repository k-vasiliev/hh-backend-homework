package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.component.EmployerMapper;
import ru.hh.school.component.IdParameterValidator;
import ru.hh.school.component.PaginationValidator;
import ru.hh.school.entity.comment.EmployerComment;
import ru.hh.school.entity.counter.EmployerCounter;

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
    private final AreaService areaService;
    private final PaginationValidator paginationValidator;
    private final IdParameterValidator idParameterValidator;

    public EmployerService(EmployerDao employerDao,
                           EmployerMapper employerMapper,
                           ApiService apiService,
                           CounterService counterService,
                           CommentService commentService,
                           AreaService areaService,
                           PaginationValidator paginationValidator,
                           IdParameterValidator idParameterValidator) {
        this.employerDao = employerDao;
        this.employerMapper = employerMapper;
        this.apiService = apiService;
        this.counterService = counterService;
        this.commentService = commentService;
        this.areaService = areaService;
        this.paginationValidator = paginationValidator;
        this.idParameterValidator = idParameterValidator;
    }

    @Transactional
    public Employer getOrCreateEmployer(Integer employerId) {
        try {
            return getEmployer(employerId);
        } catch(NotFoundException e) {
            return addNewEmployerToFavorites(employerId, "");
        }
    }

    @Transactional
    public Employer refreshOrCreateEmployer(Integer employerId) {
        try {
            return refresh(employerId);
        } catch (NotFoundException e) {
            return addNewEmployerToFavorites(employerId, "");
        }
    }

    public List<FavoriteEmployerDto> getFavorites(Integer page, Integer perPage) {
        paginationValidator.validate(page, perPage);
        List<Employer> employers = employerDao.getFavoritesWithPagination(page, perPage);
        //TODO update counter in separate thread?
        employers.stream()
                .map(employer -> employer.getId())
                .forEach(counterService::incrementEmployerCounter);
        return employers.stream().map(employerMapper::mapEntityToDto).collect(Collectors.toList());
    }

    public Employer addEmployerToFavorites(Integer employerId, String comment) {
        try {
            getEmployer(employerId);
            throw new BadRequestException("Bad request");
        } catch (NotFoundException e) {
            return addNewEmployerToFavorites(employerId, comment);
        }
    }

    @Transactional
    public Employer addNewEmployerToFavorites(Integer employerId, String comment) {
        String dataFromApi = apiService.fetchEmployersFromApiById(employerId);
        Employer employer = employerMapper.mapSingleItemFromApiToEntity(dataFromApi);
        createAreaForEmployer(employer, employer.getArea());
        createCommentForEmployer(employer, comment);
        createCounterForEmployer(employer);
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
        Employer employer =
                employerDao.getWithPessimisticLocking(employerId).orElseThrow(NotFoundException::new);
        employerDao.delete(employer);
    }

    @Transactional
    public Employer refresh(Integer employerId) {
        idParameterValidator.validate(employerId);
        Employer employer = getEmployer(employerId);
        String freshDataFromApi = apiService.fetchEmployersFromApiById(employerId);
        Employer freshEmployer = employerMapper.mapSingleItemFromApiToEntity(freshDataFromApi);
        Employer refreshedEmployer = refresh(employer, freshEmployer);
        employerDao.update(refreshedEmployer);
        return employer;
    }

    @Transactional
    private Employer getEmployer(Integer id) {
        return employerDao.get(Employer.class, id).orElseThrow(NotFoundException::new);
    }

    private EmployerComment createCommentForEmployer(Employer employer, String comment) {
        EmployerComment employerComment = commentService.createEmployerComment(comment);
        employerComment.setEmployer(employer);
        employer.setComment(employerComment);
        return employerComment;
    }

    private EmployerCounter createCounterForEmployer(Employer employer) {
        EmployerCounter counter = counterService.createEmployerCounter();
        counter.setEmployer(employer);
        employer.setViewsCount(counter);
        return counter;
    }

    private Employer refresh(Employer outdatedEmployer, Employer freshEmployer) {
        createAreaForEmployer(outdatedEmployer, freshEmployer.getArea());
        outdatedEmployer.setName(freshEmployer.getName());
        outdatedEmployer.setDescription(freshEmployer.getDescription());
        return outdatedEmployer;
    }

    private Area createAreaForEmployer(Employer employer, Area area) {
        Area employerArea = areaService.getOrCreateArea(area);
        employer.setArea(employerArea);
        return employerArea;
    }

}
