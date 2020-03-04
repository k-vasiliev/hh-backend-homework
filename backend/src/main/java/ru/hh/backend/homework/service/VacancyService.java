package ru.hh.backend.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.VacancyDao;
import ru.hh.backend.homework.entity.VacancyEntity;
import ru.hh.backend.homework.resource.VacancyResource;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class VacancyService {
    private final VacancyDao vacancyDao;
    private static Logger logger = LoggerFactory.getLogger(VacancyService.class);

    @Inject
    public VacancyService(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Transactional
    public VacancyEntity save(VacancyEntity vacancyEntity) {
        logger.info("Vacancy saved");
        return vacancyDao.save(vacancyEntity);
    }

    @Transactional
    public VacancyEntity get(Integer id) {
        return vacancyDao.get(id);
    }

    @Transactional
    public List<VacancyEntity> getAll() {
        return vacancyDao.getAll();
    }
}
