package ru.hh.nab.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.dao.CompanyDAO;
import ru.hh.nab.dto.ResponseCompanyDTO;
import ru.hh.nab.entity.Company;
import ru.hh.nab.entity.User;

import java.time.LocalDate;
import java.util.List;

public class CompanyService {

    private final CompanyDAO companyDAO;

    private final UserService userService;

    public CompanyService(CompanyDAO companyDAO, UserService userService) {
        this.companyDAO = companyDAO;
        this.userService = userService;
    }

    @Transactional
    public Company createCompany(int userId, String name) {
        User user = userService.getUsersById(userId);
        Company company = new Company(user, name, LocalDate.now(), true);
        return companyDAO.addCompany(company);
    }

    @Transactional
    public List<ResponseCompanyDTO> getAllCompany() {
        return companyDAO.getAllCompany();
    }

    @Transactional
    public Company getCompanyById(int id) {
        return companyDAO.getCompanyById(id);
    }
}
