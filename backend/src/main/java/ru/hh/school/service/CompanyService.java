package ru.hh.school.service;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.entity.Company;

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
    public Company getCompanyById(Integer id) throws NotFoundException {
        Optional<Company> company = companyDao.getCompanyByCompanyId(id);
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

    @Transactional
    public void incrementView(Integer id) {
        companyDao.incrementView(id);
    }

    @Transactional
    public void updateComment(Integer id, String comment) {
        companyDao.updateComment(id, comment);
    }

    @Transactional
    public void deleteCompany(Integer id) {
        companyDao.deleteCompany(id);
    }

    @Transactional
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }
}
