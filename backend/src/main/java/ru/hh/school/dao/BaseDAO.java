package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class BaseDAO {
    private final SessionFactory sessionFactory;

    public BaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T get(Class<T> c, Serializable id) {

        return getSession().get(c, id);
    }

    public <T> List<T> getByPage(Class<T> c, Integer page, Integer perPage) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();

        CriteriaQuery<T> cq = cb.createQuery(c);
        Root<T> root = cq.from(c);
        cq.orderBy(cb.asc(root.get("id")));

        TypedQuery<T> query = getSession().createQuery(cq);
        query.setFirstResult(calculateFirstResult(page, perPage)).setMaxResults(perPage);

        return query.getResultList();
    }

    public void saveOrUpdate(Object object) {
        if (object == null) {
            return;
        }

        getSession().saveOrUpdate(object);
    }

    public void update(Object object) {
        getSession().update(object);
    }

    public void delete(Object object) {
        getSession().delete(object);
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private int calculateFirstResult(int page, int perPage) {
        return ((perPage * page) - perPage);
    }

}
