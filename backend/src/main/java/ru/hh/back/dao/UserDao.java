package ru.hh.back.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.back.entity.UserEntity;
import ru.hh.back.entity.UserType;

import java.util.List;

@Repository
public class UserDao {
    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<UserEntity> getUser(String type) {
        var userType = UserType.valueOf(type);
        List<UserEntity> users = getSessionFactory().getCurrentSession()
                .createQuery("SELECT u From UserEntity u WHERE u.type = :type", UserEntity.class)
                .setParameter("type", userType)
                .list();
        return users;

    }

    public Integer save(UserEntity user) {
        getSessionFactory().getCurrentSession().saveOrUpdate(user);
        return user.getId();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
