package ru.hh.backend.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.VacancyDao;
import ru.hh.backend.homework.entity.VacancyEntity;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {
    private final VacancyDao vacancyDao;

    public VacancyService(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public VacancyEntity save(VacancyEntity vacancyEntity) {
        return vacancyDao.save(vacancyEntity);
    }

    @Transactional
    public Optional<VacancyEntity> get(Integer id) {
        return vacancyDao.get(id);
    }

    @Transactional
    public List<VacancyEntity> getAll() {
        return vacancyDao.getAll();
    }
}
