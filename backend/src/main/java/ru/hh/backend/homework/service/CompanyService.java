package ru.hh.backend.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.CompanyDao;
import ru.hh.backend.homework.entity.CompanyEntity;

import javax.inject.Singleton;
import java.util.List;

@Service
public class CompanyService {
    CompanyDao companyDao;

    @Autowired
    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Transactional
    public CompanyEntity create(CompanyEntity company) {
        return companyDao.save(company);
    }

    @Transactional
    public CompanyEntity get(Long id) {
        return  companyDao.get(id);
    }

    @Transactional
    public List<CompanyEntity> getAll() {
        return companyDao.getAll();
    }
}
