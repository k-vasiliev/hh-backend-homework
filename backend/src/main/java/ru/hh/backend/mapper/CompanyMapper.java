package ru.hh.backend.mapper;

import javassist.NotFoundException;
import ru.hh.backend.dto.CompanyDtoRequest;
import ru.hh.backend.dto.CompanyDtoResponse;
import ru.hh.backend.entity.Company;
import ru.hh.backend.service.UserService;

import javax.inject.Singleton;
import java.time.LocalDate;

@Singleton
public class CompanyMapper {

    UserService userService;

    public CompanyMapper(UserService userService) {
        this.userService = userService;
    }

    public CompanyDtoResponse map(Company company) {
        return new CompanyDtoResponse(company.getCompanyId(), company.getName());
    }

    public Company map(CompanyDtoRequest companyDtoRequest) throws NotFoundException {
        return new Company(
                companyDtoRequest.getName(),
                userService.getUser(companyDtoRequest.getUserId()),
                LocalDate.now());
    }
}
