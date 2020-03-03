package ru.hh.backend.dao;

import org.hibernate.SessionFactory;
import ru.hh.backend.entity.User;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> getUser(Integer id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .get(User.class, id));
    }

    public List<User> getUsersByType(String type) {
        return sessionFactory.getCurrentSession()
                .createQuery("select u from User u where u.type=:type", User.class)
                .setParameter("type", type).getResultList();

    }

    public List<User> getUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User", User.class).list();
    }

    public Integer save(User user) {
        sessionFactory.getCurrentSession()
                .save(user);
        return user.getUserId();
    }
}
