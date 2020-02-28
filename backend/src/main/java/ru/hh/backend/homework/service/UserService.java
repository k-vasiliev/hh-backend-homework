package ru.hh.backend.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.UserDao;
import ru.hh.backend.homework.entity.UserEntity;

import java.util.List;


@Service
public class UserService {

    UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public UserEntity create(UserEntity user) {
        return userDao.save(user);
    }

    public UserEntity get(Long id) {
        return userDao.get(id);
    }

    @Transactional
    public List<UserEntity> getByType(String type) {
        return userDao.getAllByType(type);
    }
}
