package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.UserRequestDto;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UserService {

  private final UserDao userDao;

  @Inject
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }
  @Transactional
  public void saveNew(UserRequestDto userDto) {
    User user = new User();
    user.setName(userDto.getName());
    user.setUserType(userDto.getUserType());
    //TODO проверить, чтобы добавлялось время
    userDao.create(user);
  }
  @Transactional
  public List<User> getUsersByType(UserType userType) {
    return userDao.getByType(userType);
  }
  @Transactional
  public List<User> getAll() {
    return userDao.getAll();
  }
  @Transactional
  public void deleteAll() {
    userDao.deleteAll();
  }
  @Transactional
  public User getBy(int userId) {
    return userDao.get(userId);
  }
  @Transactional
  public void deleteBy(int userId) {
    userDao.deleteBy(userId);
  }
}
