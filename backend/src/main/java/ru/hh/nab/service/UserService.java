package ru.hh.nab.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.dao.UserDAO;
import ru.hh.nab.entity.User;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public User addUser(String name, String type) {
        return userDAO.addUser(name, type);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    public List<User> getAllUsersByType(String type) {
        return userDAO.getAllUsersByType(type);
    }

    @Transactional
    public User getUsersById(int id) {
        return userDAO.getUsersById(id);
    }
}
