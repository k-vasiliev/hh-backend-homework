package service;

import dao.CompanyDao;
import entity.UsersEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyService {
    CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Transactional
    public List<UsersEntity> getCompanies() {
        return companyDao.getCompanies();
    }
}
