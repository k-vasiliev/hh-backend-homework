package ru.hh.nab.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.dao.CompanyDAO;
import ru.hh.nab.entity.Company;

import java.util.List;

public class CompanyService {

    private final CompanyDAO companyDAO;

    public CompanyService(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Transactional
    public Company createCompany(int userId, String name) {
        return companyDAO.addCompany(userId, name);
    }

    @Transactional
    public List<Company> getAllCompany() {
        return companyDAO.getAllCompany();
    }

    @Transactional
    public Company getCompanyById(int id) {
        return companyDAO.getCompanyById(id);
    }
}
