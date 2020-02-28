package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.hh.backend.homework.entity.UserEntity;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserEntity save(UserEntity userEntity) {
        getSession().save(userEntity);
        return userEntity;
    }

    public UserEntity get(Long id) {
        Session session = getSession();
        UserEntity user = session
                .createQuery("SELECT u FROM UserEntity u WHERE u.id = :id", UserEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return user;

    }

    public List<UserEntity> getAllByType(String userType) {
        return getSession()
                .createQuery("SELECT u FROM UserEntity u WHERE u.userType = :userType", UserEntity.class)
                .setParameter("userType", userType)
                .list();
    }

    private Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (Exception e) {
            return sessionFactory.openSession();
        }
    }
}
