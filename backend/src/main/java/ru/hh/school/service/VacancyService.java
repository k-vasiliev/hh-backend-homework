package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.mapper.VacancyMapper;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;

@Singleton
public class VacancyService {

    private final VacancyDao vacancyDao;

    private final ApiHhService apiHhService;

    private final VacancyMapper vacancyMapper;

    public VacancyService(VacancyDao vacancyDao, ApiHhService apiHhService, VacancyMapper vacancyMapper) {
        this.vacancyDao = vacancyDao;
        this.apiHhService = apiHhService;
        this.vacancyMapper = vacancyMapper;
    }

    @Transactional
    public Vacancy get(Integer vacancyId) {
        return vacancyDao.get(vacancyId).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Vacancy create(Vacancy vacancy) {
        return vacancyDao.create(vacancy);
    }

    @Transactional
    public Vacancy saveOrUpdate(Vacancy vacancy) {
        return vacancyDao.saveOrUpdate(vacancy);
    }

    @Transactional
    public void delete(Vacancy vacancy) {
        vacancyDao.delete(vacancy);
    }

    @Transactional
    public void update(Integer id) throws HhRequestException {
        if (id  == null) {
            throw new HhRequestException("employer id is empty");
        }

        VacancyDto vacancy = apiHhService.getVacancyBy(id);
        this.saveOrUpdate(vacancyMapper.map(vacancy));
    }

}
