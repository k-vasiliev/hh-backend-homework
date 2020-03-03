package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.CompanyRequestDto;
import ru.hh.school.dto.CompanyResponseDto;
import ru.hh.school.entity.Company;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
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
        User user = userDao.get(companyDto.getUserId());
        if (user.getUserType() == UserType.EMPLOYER) {
            Company company = new Company();
            company.setTitle(companyDto.getName());
            company.setUser(user);
            //TODO проверить, чтобы добавлялось время
            companyDao.create(company);
        }
    }

    @Transactional
    public List<CompanyResponseDto> getAll() {
        return companyDao.getAll().stream()
                .map(CompanyService::mapped)
                .collect(Collectors.toList());
    }

    protected static CompanyResponseDto mapped(Company company) {
        CompanyResponseDto companyDto = new CompanyResponseDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getTitle());
        return companyDto;
    }

}
