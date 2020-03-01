package ru.hh.backend.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.CompanyDao;
import ru.hh.backend.homework.entity.CompanyEntity;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CompanyService {
    private final CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Transactional
    public CompanyEntity save(CompanyEntity companyEntity) {
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
