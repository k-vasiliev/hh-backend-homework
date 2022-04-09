package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.domain.BaseEntity;

import java.util.Optional;

@Repository
public class AbstractRepository<T extends BaseEntity> {
    protected final SessionFactory sessionFactory;

    public AbstractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T save(T entity) {
        Session session = getSessionFactory().getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    public void delete(T entity) {
        Session session = getSessionFactory().getCurrentSession();
        session.delete(entity);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}