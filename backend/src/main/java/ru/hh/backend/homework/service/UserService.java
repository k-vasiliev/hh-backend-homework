package ru.hh.backend.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.UserDao;
import ru.hh.backend.homework.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public UserEntity save(UserEntity userEntity) {
        return userDao.save(userEntity);
    }

    @Transactional
    public Optional<UserEntity> get(Integer id) {
        return userDao.get(id);
    }

    @Transactional
    public List<UserEntity> getAllByType(String userType) {
        return userDao.getAllByType(userType);
    }
}
