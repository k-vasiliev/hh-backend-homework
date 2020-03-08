package ru.hh.school.service;


import ru.hh.school.UserType;
import ru.hh.school.dao.UserDao;
import ru.hh.school.entity.UserEntity;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public UserEntity create(UserEntity userEntity) {
        return userDao.create(userEntity);
    }

    @Transactional
    public UserEntity get(Long userId) {
        return userDao.get(userId);
    }

    @Transactional
    public List<UserEntity> getAll() {
        return userDao.getAllByType(UserType.APPLICANT.name());
    }
}
