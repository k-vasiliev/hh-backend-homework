package ru.hh.backend.service;

import org.springframework.stereotype.Service;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User create(User user) {
        return userDao.create(user);
    }

    @Transactional
    public User get(Long id) {
        return userDao.get(id);
    }

    @Transactional
    public List<User> getByType(String type) {
        return userDao.getByType(type);
    }
}
