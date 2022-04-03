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

    public Long save(T user) {
        Session session = getSessionFactory().getCurrentSession();
        session.saveOrUpdate(user);
        return user.getId();
    }

    public void deleteById(T user) {
        Session session = getSessionFactory().getCurrentSession();
        session.delete(user);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}