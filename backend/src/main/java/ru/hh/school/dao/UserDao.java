package ru.hh.school.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.entity.NegotiationEntity;
import ru.hh.school.entity.ResumeEntity;
import ru.hh.school.entity.UserEntity;


import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserDao {


    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public UserEntity get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = null;
        try {
            userEntity = session.get(UserEntity.class,id);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userEntity;
    }


    public UserEntity create(UserEntity userEntity) {
        session().save(userEntity);
        return userEntity;
    }

    public List<UserEntity> getAllByType(String type) {
        Session session = sessionFactory.getCurrentSession();
        List<UserEntity> userEntityList = null;
        try {
            userEntityList = session
                    .createQuery("SELECT * FROM User u WHERE u.type = :type", UserEntity.class)
                    .setParameter("type", type)
                    .getResultList();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userEntityList;
    }




}
