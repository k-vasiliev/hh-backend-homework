package ru.hh.nab.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.entity.User;

@Repository
public class UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public User addUser(String name, String type) {
        User user = new User(name, type, new Date(), true);
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Transactional
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT FROM User u", User.class)
                .getResultList();
    }
}
