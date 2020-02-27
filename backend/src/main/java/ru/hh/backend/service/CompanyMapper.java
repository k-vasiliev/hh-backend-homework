package ru.hh.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.dto.request.CompanyRequestDto;
import ru.hh.backend.dto.response.CompanyResponseDto;
import ru.hh.backend.entity.Company;

@Service
public class CompanyMapper {

    UserDao userDao;

    @Autowired
    public CompanyMapper(UserDao userDao) {
        this.userDao = userDao;
    }

    public Company map(CompanyRequestDto companyRequestDto) {
        Company company = new Company();
        company.setCompanyName(companyRequestDto.getName());
        company.setUser(userDao.get(companyRequestDto.getUserId()));
        return company;
        //return new Company(companyRequestDto.getName(), userDao.get(companyRequestDto.getUserId()));
    }

    public CompanyResponseDto map(Company company) {
        return new CompanyResponseDto(company.getId(), company.getCompanyName());
    }
}
