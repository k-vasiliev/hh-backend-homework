package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.backend.homework.entity.UserEntity;
import ru.hh.backend.homework.enums.UserType;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserDao {
    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserEntity save(UserEntity userEntity) {
        getSessionFactory().getCurrentSession().save(userEntity);
        return userEntity;
    }

    public Optional<UserEntity> get(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        try {
            UserEntity user = session
                    .createQuery("SELECT u FROM UserEntity u WHERE u.id = :id", UserEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(user);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public List<UserEntity> getAllByType(UserType userType) {
        return getSessionFactory()
                .getCurrentSession()
                .createQuery("SELECT u FROM UserEntity u WHERE u.user_type = :userType", UserEntity.class)
                .setParameter("userType", userType)
                .getResultList();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
