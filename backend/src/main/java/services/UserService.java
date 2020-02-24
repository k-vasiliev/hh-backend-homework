package services;

import dao.UserDao;
import models.User;
import org.hibernate.SessionFactory;
import utils.TransactionHelper;

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

  public Set<User> getAll() {
    return th.inTransaction(userDao::getAll);
  }

  public void deleteAll() {
    th.inTransaction(userDao::deleteAll);
  }

  public void saveNew(User user) {
    th.inTransaction(() -> userDao.saveNew(user));
  }

  public Optional<User> getBy(int userId) {
    return th.inTransaction(() -> userDao.getBy(userId));
  }

  public void deleteBy(int userId) {
    th.inTransaction(() -> userDao.deleteBy(userId));
  }

  public void update(User user) {
    th.inTransaction(() -> userDao.update(user));
  }

  public void changeFullName(int userId, String firstName, String lastName) {
    th.inTransaction(() -> {
      userDao.getBy(userId)
        .stream()
        .peek(user -> user.setFirstName(firstName))
        .forEach(user -> user.setLastName(lastName));
      // хибер отслеживает изменения сущностей и выполняет sql update перед коммитом транзакции
    });
  }

}
