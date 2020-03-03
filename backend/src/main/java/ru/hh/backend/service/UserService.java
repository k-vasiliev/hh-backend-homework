package ru.hh.backend.service;

import javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.entity.User;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User getUser(Integer id) throws NotFoundException {
        Optional<User> user = userDao.getUser(id);
        if (user.isEmpty())
            throw new NotFoundException("User not found");
        else
            return user.get();
    }

    @Transactional
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Transactional
    public List<User> getUsersByType(String type) {
        return userDao.getUsersByType(type);
    }

    @Transactional
    public Integer save(User user) {
        return userDao.save(user);
    }

}
