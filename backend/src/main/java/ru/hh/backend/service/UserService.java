package ru.hh.backend.service;

import org.springframework.stereotype.Service;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.entity.UserEntity;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public UserEntity create(UserEntity user) {
        return userDao.create(user);
    }

    public UserEntity get(Long id) {
        return userDao.get(id);
    }

    @Transactional
    public List<UserEntity> getByType(String type) {
        return userDao.getByType(type);
    }
}
