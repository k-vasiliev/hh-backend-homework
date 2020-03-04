package ru.hh.backend.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.CompanyDao;
import ru.hh.backend.homework.entity.CompanyEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CompanyService {
    private final CompanyDao companyDao;
    private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Inject
    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Transactional
    public CompanyEntity save(CompanyEntity companyEntity) {
        logger.info("Company saved");
        return companyDao.save(companyEntity);
    }

    @Transactional
    public CompanyEntity get(Integer id) {
        return companyDao.get(id);
    }

    @Transactional
    public List<CompanyEntity> getAll() {
        return companyDao.getAll();
    }
}
