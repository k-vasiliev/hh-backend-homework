package ru.hh.school.service;

import ru.hh.school.dao.UserDao;
import ru.hh.school.entity.UsersEntity;
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
    public Integer addUser(String name, boolean isCompany) {
        UsersEntity newUser = new UsersEntity(name, isCompany);
        return userDao.createUser(newUser);
    }

    @Transactional
    public List<UsersEntity> getUsers(boolean isCompany) {
        return userDao.getUsers(isCompany);
    }

    @Transactional
    public UsersEntity getUserById(Integer id) {
        return userDao.getUserById(id);
    }

}
