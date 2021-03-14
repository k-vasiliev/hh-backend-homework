package ru.hh.school.service;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.FavoritesVacancyDao;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.FavoritesVacancy;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.exception.ConstraintException;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.exception.InternalException;
import ru.hh.school.mapper.VacancyMapper;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Singleton
public class FavoritesVacancyService {

    private final ApiHhService apiHhService;

    private final FavoritesVacancyDao favoritesVacancyDao;

   private final VacancyMapper vacancyMapper;

   private final VacancyService vacancyService;

    public FavoritesVacancyService(ApiHhService apiHhService, FavoritesVacancyDao favoritesVacancyDao, VacancyMapper vacancyMapper, VacancyService vacancyService) {
        this.apiHhService = apiHhService;
        this.favoritesVacancyDao = favoritesVacancyDao;
        this.vacancyMapper = vacancyMapper;
        this.vacancyService = vacancyService;
    }


    @Transactional
    public FavoritesVacancy get(Integer vacancyId) {
        return favoritesVacancyDao.get(vacancyId).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public FavoritesVacancy saveOrUpdate(FavoritesVacancy vacancy) {
        return favoritesVacancyDao.saveOrUpdate(vacancy);
    }

    @Transactional
    public FavoritesVacancy create(Integer id, String comment) throws HhRequestException, ConstraintException {
        if (id == null) {
            throw new HhRequestException("vacancy id is empty");
        }
        // иду в апи хх, чтобы сохранить данные об вакансии
        // TODO можно ли сделать в отдельном не блокирующем потоке?
        // TODO если операция будет долгой или свалится с ошибкой что будет с транзакцией?
        VacancyDto vacancyDto = apiHhService.getVacancyBy(id);
        Vacancy vacancy = vacancyMapper.map(vacancyDto);
        vacancyService.saveOrUpdate(vacancy);


        //TODO очень плохо пахнет.
        // Как при создании отловить ошибку org.hibernate.exception.ConstraintViolationException ?
        Optional<FavoritesVacancy> favoritesVacancyOptional = favoritesVacancyDao.get(id);
        if (favoritesVacancyOptional.isPresent()) {
            throw new ConstraintException("Favorites vacancy already exist");
        }

        FavoritesVacancy favoritesVacancy = new FavoritesVacancy(id);
        return favoritesVacancyDao.create(favoritesVacancy);
    }

    @Transactional
    public List<FavoritesVacancy> getAll(Integer limit, Integer page) throws InternalException {
        final int defaultLimit = 100;
        final int defaultPage = 0;
        limit = limit == null ? defaultLimit : limit;
        page = page == null ? defaultPage : page;

        List<FavoritesVacancy> favoritesVacancyList;
        try {
            favoritesVacancyList = favoritesVacancyDao.getAll(limit, page);
        } catch (HibernateException e) {
            throw new InternalException(e.getMessage());
        }

        favoritesVacancyList.stream()
                .peek(vacancy -> vacancy.setViewsCount(vacancy.getViewsCount() + 1))
                .forEach(favoritesVacancyDao::update);

        return favoritesVacancyList;
    }

    @Transactional
    public void delete(Integer id) throws InternalException {
        if (id == null) {
            throw new InternalException("vacancy id is empty") ;
        }

        favoritesVacancyDao.delete(new FavoritesVacancy(id));
    }

    @Transactional
    public FavoritesVacancy update(Integer id, String comment) {
        return (id == null || comment == null) ? null : favoritesVacancyDao.update(
                new FavoritesVacancy(id, comment, LocalDateTime.now()));
    }

}
