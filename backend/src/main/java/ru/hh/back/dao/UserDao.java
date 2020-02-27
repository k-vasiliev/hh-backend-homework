package ru.hh.back.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.back.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private final SessionFactory sessionFactory;
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<UserEntity> getUser(String type) {
        Session session = getSessionFactory().getCurrentSession();
        try {
            List<UserEntity> users = session.createQuery("SELECT u From UserEntity u WHERE u.type = :type", UserEntity.class)
                    .setParameter("type", type)
                    .list();
            return users;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Transactional
    public Integer save(UserEntity user) {
        Session session = getSessionFactory().getCurrentSession();
        session.saveOrUpdate(user);
        return user.getId();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
