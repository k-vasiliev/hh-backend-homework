package ru.hh.backend.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.backend.entity.UserEntity;

import java.util.List;

@Repository
public class UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserEntity create(UserEntity user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    public UserEntity get(Long id) {
        return sessionFactory.getCurrentSession().get(UserEntity.class, id);
    }

    public List<UserEntity> getByType(String userType) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u From UserEntity u WHERE u.userType = :userType", UserEntity.class)
                .setParameter("userType", userType)
                .getResultList();
    }

}
