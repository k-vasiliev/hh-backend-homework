package ru.hh.backend.dao;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.model.User;

import java.util.List;

@Repository
public class UserDao {
  private final SessionFactory sessionFactory;

  public UserDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional
  public User create(User user) {
    sessionFactory.getCurrentSession().save(user);
    return user;
  }

  @Transactional
  public User getReference(Long id) {
    return sessionFactory.getCurrentSession().load(User.class, id);
  }

  @Transactional
  public List<User> getByType(String userType) {
    return sessionFactory.getCurrentSession()
        .createQuery("FROM User u WHERE u.userType = :userType", User.class)
        .setParameter("userType", userType)
        .getResultList();
  }

}
