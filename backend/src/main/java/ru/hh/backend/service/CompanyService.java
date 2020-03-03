package ru.hh.backend.service;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.dao.CompanyDao;
import ru.hh.backend.entity.Company;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class CompanyService {

    private CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Transactional
    public Company getCompany(Integer id) throws NotFoundException {
        Optional<Company> company = companyDao.getCompany(id);
        if (company.isEmpty())
            throw new NotFoundException("Company not found");
        else
            return company.get();
    }

    @Transactional
    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }

    @Transactional
    public Integer save(Company company) {
        return companyDao.save(company);
    }
}
