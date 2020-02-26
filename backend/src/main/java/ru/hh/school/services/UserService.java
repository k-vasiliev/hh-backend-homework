package ru.hh.school.services;

import ru.hh.school.dao.UserDao;
import ru.hh.school.models.User;
import org.hibernate.SessionFactory;
import ru.hh.school.utils.TransactionHelper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserService {

  private final UserDao userDao;
  private final TransactionHelper th;

  public UserService(
          SessionFactory sessionFactory,
          UserDao userDao) {
    this.userDao = userDao;
    this.th = new TransactionHelper(sessionFactory);
  }

  public List<User> getAll() {
    return th.inTransaction(userDao::getAll);
  }

  public void deleteAll() {
    th.inTransaction(userDao::deleteAll);
  }

  public void saveNew(User user) {
    th.inTransaction(() -> userDao.create(user));
  }

  public User getBy(int userId) {
    return th.inTransaction(() -> userDao.get(userId));
  }

  public void deleteBy(int userId) {
    th.inTransaction(() -> userDao.deleteBy(userId));
  }

  public void update(User user) {
    th.inTransaction(() -> userDao.update(user));
  }

  public void changeName(int userId, String name) {
    th.inTransaction(() -> {
      userDao.get(userId).setName(name);
      // хибер отслеживает изменения сущностей и выполняет sql update перед коммитом транзакции
    });
  }

}
