package ru.hh.school.service;


import ru.hh.school.dao.CompanyDao;
import ru.hh.school.entity.CompanyEntity;

import javax.transaction.Transactional;
import java.util.List;

public class CompanyService {
    private final CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Transactional
    public CompanyEntity create(CompanyEntity companyEntity) {
        return companyDao.create(companyEntity);
    }

    @Transactional
    public CompanyEntity get(Long companyId) {
        return companyDao.get(companyId);
    }

    @Transactional
    public List<CompanyEntity> getAll() {
        return companyDao.getAll();
    }
}
