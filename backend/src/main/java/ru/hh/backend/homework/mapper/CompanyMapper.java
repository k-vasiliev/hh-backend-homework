package ru.hh.backend.homework.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dao.UserDao;
import ru.hh.backend.homework.dto.request.CompanyRequestDto;
import ru.hh.backend.homework.dto.response.CompanyResponseDto;
import ru.hh.backend.homework.entity.CompanyEntity;

import javax.inject.Singleton;

@Service
public class CompanyMapper {
    UserDao userDao;

    @Autowired
    public CompanyMapper(UserDao userDao) {
        this.userDao = userDao;
    }

    public CompanyEntity map(CompanyRequestDto companyRequestDto) {
        CompanyEntity company = new CompanyEntity();
        company.setName(companyRequestDto.getName());
        company.setUser(userDao.get(companyRequestDto.getUserId()));
        return company;
    }

    public CompanyResponseDto map(CompanyEntity company) {
        return new CompanyResponseDto(company.getId(), company.getName());

    }
}
