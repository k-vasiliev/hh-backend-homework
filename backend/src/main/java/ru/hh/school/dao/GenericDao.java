package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository
@Transactional
public class GenericDao {

    private final SessionFactory sessionFactory;

    public GenericDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T get(Class<T> clz, Serializable id) {
        return getSession().get(clz, id);
    }

    public void save(Object obj) {
        if (obj == null) {
            return;
        }
        getSession().save(obj);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
