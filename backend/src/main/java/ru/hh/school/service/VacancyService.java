package ru.hh.school.service;


import ru.hh.school.dao.VacancyDao;
import ru.hh.school.entity.VacancyEntity;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class VacancyService {
    private final VacancyDao vacancyDao;

    public VacancyService(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public VacancyEntity create(VacancyEntity vacancyEntity) {
        return vacancyDao.create(vacancyEntity);
    }

    @Transactional
    public VacancyEntity get(Long vacancyId) {
        return vacancyDao.get(vacancyId).orElse(null);
    }

    @Transactional
    public List<VacancyEntity> getAll() {
        return vacancyDao.getAll();
    }
}

