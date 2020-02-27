package ru.hh.backend.service;

import org.springframework.stereotype.Service;
import ru.hh.backend.dao.VacancyDao;
import ru.hh.backend.entity.Vacancy;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VacancyService {
    VacancyDao vacancyDao;

    public VacancyService(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public Vacancy create(Vacancy vacancy) {
        return vacancyDao.create(vacancy);
    }

    @Transactional
    public Vacancy get(Long id) {
        return vacancyDao.get(id);
    }

    @Transactional
    public List<Vacancy> getAll() {
        return vacancyDao.getAll();
    }
}
