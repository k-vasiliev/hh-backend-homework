package ru.hh.school.services;

import org.springframework.stereotype.Service;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.UserRequestDto;
import ru.hh.school.models.User;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserService {

  private final UserDao userDao;

  @Inject
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public void saveNew(UserRequestDto userDto) {
    User user = new User();
    user.setName(userDto.getName());
    user.setUserType(userDto.getUserType());
    //TODO проверить, чтобы добавлялось время
    userDao.create(user);
  }

  public List<User> getUsersByType(Integer userType) {
    return userDao.getByType(userType);
  }

  public List<User> getAll() {
    return userDao.getAll();
  }

  public void deleteAll() {
    userDao.deleteAll();
  }

  public User getBy(int userId) {
    return userDao.get(userId);
  }

  public void deleteBy(int userId) {
    userDao.deleteBy(userId);
  }
}
