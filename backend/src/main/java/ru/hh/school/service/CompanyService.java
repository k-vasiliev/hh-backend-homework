package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.request.CompanyRequestDto;
import ru.hh.school.dto.response.CompanyResponseDto;
import ru.hh.school.entity.Company;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CompanyService {

    private CompanyDao companyDao;
    private UserDao userDao;

    @Inject
    public CompanyService(CompanyDao companyDao, UserDao userDao) {
        this.userDao = userDao;
        this.companyDao = companyDao;
    }

    @Transactional
    public void saveNew(CompanyRequestDto companyDto) {
        User user = userDao.get(companyDto.getUserId()).orElseThrow(NotFoundException::new);
        if (user.getUserType() != UserType.EMPLOYER) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        companyDao.create(mapToEntity(companyDto, user));
    }

    @Transactional
    public List<CompanyResponseDto> getAll() {
        return companyDao.getAll().stream()
                .map(CompanyService::mapToDto)
                .collect(Collectors.toList());
    }

    private Company mapToEntity(CompanyRequestDto companyDto, User user) {
        Company company = new Company();
        company.setTitle(companyDto.getName());
        company.setUser(user);
        return company;
    }

    protected static CompanyResponseDto mapToDto(Company company) {
        CompanyResponseDto companyDto = new CompanyResponseDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getTitle());
        return companyDto;
    }
}
