package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.FavoriteVacancyDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.component.IdParameterValidator;
import ru.hh.school.component.PaginationValidator;
import ru.hh.school.component.VacancyMapper;
import ru.hh.school.entity.comment.VacancyComment;
import ru.hh.school.entity.counter.VacancyCounter;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    private final ApiService apiService;
    private final VacancyDao vacancyDao;
    private final VacancyMapper vacancyMapper;
    private final PaginationValidator paginationValidator;
    private final CounterService counterService;
    private final IdParameterValidator idParameterValidator;
    private final EmployerService employerService;
    private final CommentService commentService;
    private final AreaService areaService;

    public VacancyService(ApiService apiService,
                          VacancyDao vacancyDao,
                          VacancyMapper vacancyMapper,
                          PaginationValidator paginationValidator,
                          CounterService counterService,
                          IdParameterValidator idParameterValidator,
                          EmployerService employerService,
                          CommentService commentService,
                          AreaService areaService) {
        this.apiService = apiService;
        this.vacancyDao = vacancyDao;
        this.vacancyMapper = vacancyMapper;
        this.paginationValidator = paginationValidator;
        this.counterService = counterService;
        this.idParameterValidator = idParameterValidator;
        this.employerService = employerService;
        this.commentService = commentService;
        this.areaService = areaService;
    }

    @Transactional
    private Vacancy getVacancy(Integer id) {
        idParameterValidator.validate(id);
        return vacancyDao.get(Vacancy.class, id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public List<FavoriteVacancyDto> getFavorites(Integer page, Integer perPage) {
        paginationValidator.validate(page, perPage);
        List<Vacancy> vacancies = vacancyDao.getFavoritesWithPagination(page, perPage);
        //Better to merge vacancies after counter increment or to show views_count + 1 ?
        vacancies.stream()
                .forEach(vacancy -> {
                    counterService.incrementVacancyCounter(vacancy.getId());
                    counterService.incrementEmployerCounter(vacancy.getEmployer().getId());
                });
        return vacancies.stream().map(vacancyMapper::mapEntityToDto).collect(Collectors.toList());
    }

    @Transactional
    public void addVacancyToFavorites(Integer vacancyId, String comment) {
        try {
            getVacancy(vacancyId);
            throw new BadRequestException("Bad request");
        } catch(NotFoundException e) {
            addNewFavoriteVacancy(vacancyId, comment);
        }
    }

    @Transactional
    public void deleteVacancy(Integer vacancyId) {
        Vacancy vacancy = vacancyDao
                .getWithPessimisticLocking(vacancyId).orElseThrow(NotFoundException::new);
        vacancyDao.delete(vacancy);
    }

    @Transactional
    public Vacancy refresh(Integer vacancyId) {
        Vacancy vacancy = getVacancy(vacancyId);
        String dataFromApi = apiService.fetchVacanciesFromApiById(vacancyId);
        Vacancy freshVacancy = vacancyMapper.mapSingleItemFromApiToEntity(dataFromApi);
        Vacancy refreshedVacancy = refresh(vacancy, freshVacancy);
        vacancyDao.update(refreshedVacancy);
        return refreshedVacancy;
    }

    private Vacancy addNewFavoriteVacancy(Integer vacancyId, String comment) {
        String dataFromApi = apiService.fetchVacanciesFromApiById(vacancyId);
        Vacancy vacancy = vacancyMapper.mapSingleItemFromApiToEntity(dataFromApi);
        createCommentForVacancy(vacancy, comment);
        createCounterForVacancy(vacancy);
        createAreaForVacancy(vacancy, vacancy.getArea());
        createEmployerForVacancy(vacancy, vacancy.getEmployer());
        vacancyDao.save(vacancy);
        return vacancy;
    }

    private VacancyComment createCommentForVacancy(Vacancy vacancy, String comment) {
        VacancyComment vacancyComment = commentService.createVacancyComment(comment);
        vacancyComment.setVacancy(vacancy);
        vacancy.setComment(vacancyComment);
        return vacancyComment;
    }

    private VacancyCounter createCounterForVacancy(Vacancy vacancy) {
        VacancyCounter vacancyCounter = counterService.createVacancyCounter();
        vacancyCounter.setVacancy(vacancy);
        vacancy.setViewsCount(vacancyCounter);
        return vacancyCounter;
    }

    private Area createAreaForVacancy(Vacancy vacancy, Area area) {
        Area vacancyArea = areaService.getOrCreateArea(area);
        vacancy.setArea(vacancyArea);
        return vacancyArea;
    }

    private Employer createEmployerForVacancy(Vacancy vacancy, Employer employer) {
        Employer vacancyEmployer =  employerService.getOrCreateEmployer(employer.getId());
        vacancy.setEmployer(vacancyEmployer);
        return vacancyEmployer;
    }

    private Employer refreshEmployerForVacancy(Vacancy vacancy, Employer employer) {
        Employer vacancyEmployer =  employerService.refreshOrCreateEmployer(employer.getId());
        vacancy.setEmployer(vacancyEmployer);
        return vacancyEmployer;
    }

    private Vacancy refresh(Vacancy outdatedVacancy, Vacancy freshVacancy) {
        createAreaForVacancy(outdatedVacancy, freshVacancy.getArea());
        refreshEmployerForVacancy(outdatedVacancy, freshVacancy.getEmployer());
        outdatedVacancy.setSalary(freshVacancy.getSalary());
        outdatedVacancy.setCreatedAt(freshVacancy.getCreatedAt());
        outdatedVacancy.setName(freshVacancy.getName());
        return outdatedVacancy;
    }

}
