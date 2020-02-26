package ru.hh.backend.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.CompanyDao;
import ru.hh.backend.homework.entity.CompanyEntity;

import java.util.List;
import java.util.Optional;

@Service
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
    public Optional<CompanyEntity> get(Integer id) {
        return companyDao.get(id);
    }

    @Transactional
    public List<CompanyEntity> getAll() {
        return companyDao.getAll();
    }
}
