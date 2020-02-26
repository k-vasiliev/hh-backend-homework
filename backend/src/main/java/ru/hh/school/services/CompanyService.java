package ru.hh.school.services;

import org.springframework.stereotype.Service;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.CompanyRequestDto;
import ru.hh.school.models.Company;
import ru.hh.school.models.User;

import javax.inject.Inject;
import java.util.List;

@Service
public class CompanyService {

    private CompanyDao companyDao;
    private UserDao userDao;

    @Inject
    public CompanyService(CompanyDao companyDao, UserDao userDao) {
        this.userDao = userDao;
        this.companyDao = companyDao;
    }

    public void saveNew(CompanyRequestDto companyDto) {
        User user = userDao.get(companyDto.getUserId());
        if (user.getUserType() == 1) {
            Company company = new Company();
            company.setTitle(companyDto.getTitle());
            company.setUser(user);
            //TODO проверить, чтобы добавлялось время
            companyDao.create(company);
        }
    }

    public List<Company> getAll() {
        return companyDao.getAll();
    }

}
