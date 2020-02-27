package ru.hh.backend.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.backend.entity.User;

import java.util.List;

@Repository
public class UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User create(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    public User get(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    public List<User> getByType(String userType) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM User u WHERE u.userType = :userType", User.class)
                .setParameter("userType", userType)
                .getResultList();
    }

}
