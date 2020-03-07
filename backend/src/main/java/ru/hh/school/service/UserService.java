package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.request.UserRequestDto;
import ru.hh.school.dto.response.UserResponseDto;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class UserService {

  private final UserDao userDao;

  @Inject
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Transactional
  public void saveNew(UserRequestDto userDto) {
    userDao.create(mapToEntity(userDto));
  }

  @Transactional
  public List<UserResponseDto> getUsersDtoByType(UserType userType) {
    return userDao.getByType(userType).stream()
            .map(UserService::mapToDto)
            .collect(Collectors.toList());
  }

  private User mapToEntity(UserRequestDto userDto) {
    User user = new User();
    user.setName(userDto.getName());
    user.setUserType(userDto.getType());
    return user;
  }

  protected static UserResponseDto mapToDto(User user) {
    UserResponseDto userDto = new UserResponseDto();
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    return userDto;
  }
}
