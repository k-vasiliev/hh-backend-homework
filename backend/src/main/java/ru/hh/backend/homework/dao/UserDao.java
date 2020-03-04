package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.backend.homework.entity.UserEntity;
import ru.hh.backend.homework.enums.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserDao {
    private final SessionFactory sessionFactory;

    @Inject
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserEntity save(UserEntity userEntity) {
        sessionFactory.getCurrentSession().save(userEntity);
        return userEntity;
    }

    public UserEntity get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity user = session
                .createQuery("SELECT u FROM UserEntity u WHERE u.id = :id", UserEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(user).orElseGet(null);
    }

    public List<UserEntity> getAllByType(UserType userType) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("SELECT u FROM UserEntity u WHERE u.userType = :userType", UserEntity.class)
                .setParameter("userType", userType)
                .getResultList();
    }
}
