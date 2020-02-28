package ru.hh.backend.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.VacancyDao;
import ru.hh.backend.homework.entity.VacancyEntity;

import java.util.List;

@Service
public class VacancyService {
    VacancyDao vacancyDao;

    @Autowired
    public VacancyService(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public VacancyEntity create(VacancyEntity vacancy) {
        return vacancyDao.save(vacancy);
    }

    @Transactional
    public VacancyEntity get(Long id) {
        return vacancyDao.get(id);
    }

    @Transactional
    public List<VacancyEntity> getAll() {
        return vacancyDao.getAll();
    }
}
