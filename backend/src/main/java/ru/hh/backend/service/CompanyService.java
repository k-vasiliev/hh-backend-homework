package ru.hh.backend.service;

import org.springframework.stereotype.Service;
import ru.hh.backend.dao.CompanyDao;
import ru.hh.backend.entity.Company;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyService {
    CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Transactional
    public Company create(Company company) {
        return companyDao.create(company);
    }

    @Transactional
    public Company get(Long id) {
        return  companyDao.get(id);
    }

    @Transactional
    public List<Company> getAll() {
        return companyDao.getAll();
    }
}
