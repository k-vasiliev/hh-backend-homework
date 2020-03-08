package ru.hh.school.dao;

import ru.hh.school.entity.UsersEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Integer createUser(UsersEntity newUser) {
        sessionFactory.getCurrentSession().save(newUser);
        return newUser.getId();
    }

    public List<UsersEntity> getUsers(boolean isCompany) {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From UsersEntity a Where a.isCompany=:type", UsersEntity.class)
                .setParameter("type", isCompany)
                .list();

    }


    public UsersEntity getUserById(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From UsersEntity a Where a.id=:id", UsersEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
