package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;

@Repository
@Transactional
public class GenericDao {

    private final SessionFactory sessionFactory;

    public GenericDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> Optional<T> get(Class<T> clz, Serializable id) {
            T instance =  getSession().get(clz, id);
            return instance == null ? Optional.empty() : Optional.of(instance);
    }

    public void save(Object obj) {
        if (obj == null) {
            return;
        }
        getSession().save(obj);
    }

    public void delete(Object obj) {
        if (obj == null) {
            return;
        }
        getSession().delete(obj);
    }

    public void refresh(Object obj) {
        if (obj == null) {
            return;
        }
        getSession().refresh(obj);
    }

    public void update(Object obj) {
        if (obj == null) {
            return;
        }
        getSession().update(obj);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
