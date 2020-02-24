package ru.hh.school.service;

import org.springframework.stereotype.Service;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.UserDto;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserDao userDao;

    @Inject
    public UserService(UserDao userDao) { this.userDao = userDao;}

    @Transactional
    public void createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setType(userDto.getType());
        userDao.save(user);
    }

    @Transactional
    public List<UserDto> getUsers(UserType userType) {
        return userDao.getUsers(userType).stream()
            .map(UserService::convert).collect(Collectors.toList());
    }

    public static UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setType(user.getType());
        return userDto;
    }
}
