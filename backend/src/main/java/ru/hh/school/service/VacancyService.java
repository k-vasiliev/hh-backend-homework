package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.FavoriteVacancyDto;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.util.IdParameterValidator;
import ru.hh.school.util.PaginationValidator;
import ru.hh.school.util.VacancyMapper;

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


    public VacancyService(ApiService apiService, VacancyDao vacancyDao, VacancyMapper vacancyMapper, PaginationValidator paginationValidator, CounterService counterService, IdParameterValidator idParameterValidator) {
        this.apiService = apiService;
        this.vacancyDao = vacancyDao;
        this.vacancyMapper = vacancyMapper;
        this.paginationValidator = paginationValidator;
        this.counterService = counterService;
        this.idParameterValidator = idParameterValidator;
    }

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
            String dataFromApi = apiService.fetchVacanciesFromApiById(vacancyId);
            vacancyMapper.mapApiDataToEntityAndSave(dataFromApi, comment);
        }
    }

    @Transactional
    public void deleteVacancy(Integer vacancyId) {
        Vacancy vacancy = vacancyDao.getWithPessimisticLocking(vacancyId).orElseThrow(NotFoundException::new);
        vacancyDao.delete(vacancy);
    }

    @Transactional
    public Vacancy refresh(Integer vacancyId) {
        Vacancy vacancy = getVacancy(vacancyId);
        String dataFromApi = apiService.fetchVacanciesFromApiById(vacancyId);
        VacancyDto vacancyDto = vacancyMapper.mapDataFromApiById(dataFromApi);
        return vacancyMapper.refreshVacancy(vacancy, vacancyDto);
    }


}
