package service;

import dao.CompanyDao;
import dao.UserDao;
import entity.UsersEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void addUser(String name, boolean isCompany) {
        UsersEntity newUser = new UsersEntity(name, isCompany);
        userDao.createUser(newUser);
    }

    @Transactional
    public List<UsersEntity> getUsers(boolean isCompany) {
        return userDao.getUsers(isCompany);
    }

}
