package ru.hh.nab.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.entity.Users;

@Repository
public class UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Users addUser(String name, String type) {
        Users user = new Users(name, type, new Date(), true);
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Transactional
    public List<Users> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Users", Users.class)
                .getResultList();
    }
}
