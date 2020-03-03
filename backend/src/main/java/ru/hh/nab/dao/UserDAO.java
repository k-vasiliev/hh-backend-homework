package ru.hh.nab.dao;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.nab.entity.User;

@Repository
public class UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User addUser(String name, String type) {
        User user = new User(name, type, LocalDate.now(), true);
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where active = true", User.class)
                .getResultList();
    }

    public List<User> getAllUsersByType(String type) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where type = :paramType and active = true", User.class)
                .setParameter("paramType", type)
                .getResultList();
    }

    public User getUsersById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where userId = :paramId", User.class)
                .setParameter("paramId", id)
                .getSingleResult();
    }
}
