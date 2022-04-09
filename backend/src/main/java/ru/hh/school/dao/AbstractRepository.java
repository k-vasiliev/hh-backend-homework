package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.hh.school.domain.BaseEntity;

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

    protected long count(Query query) {
        Number result = (Number) query.uniqueResult();
        return result == null ? 0 : result.longValue();
    }

    public void delete(T entity) {
        Session session = getSessionFactory().getCurrentSession();
        session.delete(entity);
    }

    protected int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}