package ru.hh.school.service;

import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.request.CreateCompanyDto;
import ru.hh.school.dto.response.CompanyDto;
import ru.hh.school.entity.Company;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CompanyService {

    private CompanyDao companyDao;
    private UserDao userDao;

    @Inject
    public CompanyService(CompanyDao companyDao, UserDao userDao) {
        this.companyDao = companyDao;
        this.userDao = userDao;
    }

    @Transactional
    public void createCompany(CreateCompanyDto companyDto) {
        User user = userDao.getUser(companyDto.getUserId());
        if (user == null || user.getType() != UserType.employer) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setUser(user);
        company.setCreatedAt(Instant.now());
        company.setUpdatedAt(Instant.now());
        companyDao.save(company);
    }

    @Transactional
    public List<CompanyDto> getCompanies() {
        return companyDao.getCompanies().stream()
            .map(CompanyService::convert).collect(Collectors.toList());
    }

    public static CompanyDto convert(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getCompanyId());
        companyDto.setName(company.getName());
        return companyDto;
    }

}
