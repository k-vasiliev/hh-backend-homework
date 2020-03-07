package ru.hh.nab.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.dao.UserDAO;
import ru.hh.nab.dto.ResponseUserDTO;
import ru.hh.nab.entity.User;
import ru.hh.nab.entity.UserType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public User addUser(String name, UserType type) {
        return userDAO.addUser(new User(name, type, LocalDate.now(), true));
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    public List<ResponseUserDTO> getAllUsersResponseByType(UserType type) {
        return userDAO.getAllUsersByType(type).stream()
                .map(user -> new ResponseUserDTO(user.getUserName(), user.getUserId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public User getUsersById(int id) {
        return userDAO.getUsersById(id);
    }
}
