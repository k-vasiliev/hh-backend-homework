package ru.hh.nab.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.nab.entity.User;
import ru.hh.nab.entity.UserType;

@Repository
public class UserDAO {

    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where active = true", User.class)
                .getResultList();
    }

    public List<User> getAllUsersByType(UserType type) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where type = :paramType and active = true", User.class)
                .setParameter("paramType", type)
                .getResultList();
    }

    public User getUsersById(int id) {
        return sessionFactory.getCurrentSession()
                .byId(User.class).load(id);
    }

}
