package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;

public class AbstractDao {

    @Inject
    private SessionFactory sessionFactory;

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Object entity) {
        session().save(entity);
    }

    protected CriteriaBuilder builder() {
        return session().getCriteriaBuilder();
    }
}
