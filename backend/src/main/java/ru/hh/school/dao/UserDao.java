package ru.hh.school.dao;

import ru.hh.school.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public User create(User user) {
        session().save(user);
        return user;
    }

    @Transactional
    public List<User> getAll() {
        return session().createQuery("FROM User", User.class)
                .getResultList();
    }

    @Transactional
    public User get(Integer id) {
        return session().get(User.class, id);
    }

    @Transactional
    public List<User> getByType(Integer userType) {
        return session().createQuery("FROM User WHERE userType = :userType", User.class)
                .setParameter("userType", userType)
                .getResultList();
    }

    @Transactional
    public void deleteBy(Integer id) {
      session().createQuery("DELETE FROM User WHERE id = :id_to_delete")
              .setParameter("id_to_delete", id).executeUpdate();
    }

    @Transactional
    public void deleteAll() {
        session().createQuery("DELETE FROM User").executeUpdate();
    }

    @Transactional
    public void update(User user) {
        session().update(user);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
