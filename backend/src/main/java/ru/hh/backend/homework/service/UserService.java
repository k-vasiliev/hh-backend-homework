package ru.hh.backend.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.dao.UserDao;
import ru.hh.backend.homework.entity.UserEntity;
import ru.hh.backend.homework.enums.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UserService {
    private final UserDao userDao;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Inject
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public UserEntity save(UserEntity userEntity) {
        logger.info("User saved");
        return userDao.save(userEntity);
    }

    @Transactional
    public UserEntity get(Integer id) {
        return userDao.get(id);
    }

    @Transactional
    public List<UserEntity> getAllByType(UserType userType) {
        return userDao.getAllByType(userType);
    }
}
