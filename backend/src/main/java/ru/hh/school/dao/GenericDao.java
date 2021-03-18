package ru.hh.school.dao;

import java.io.Serializable;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class GenericDao {

  private final SessionFactory sessionFactory;

  @Inject
  protected GenericDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  protected void save(Object object) {
    if (object == null)
      return;
    getSession().save(object);
  }

  protected <T> T get(Class<T> clazz, Serializable id) {
    return getSession().get(clazz, id);
  }

  protected void update(Object object) {
    if (object == null)
      return;
    getSession().update(object);
  }

  protected void delete(Object object) {
    if (object == null)
      return;
    getSession().delete(object);
  }

  protected <T> CriteriaQuery<T> getOrderbyCriteria(Class<T> clazz, Order order,
      String orderField, String ...joinFields) {
    CriteriaBuilder cb = getSession().getCriteriaBuilder();
    CriteriaQuery<T> query = cb.createQuery(clazz);
    Root<T> root = query.from(clazz);
    Stream.of(joinFields).forEach(root::fetch);
    query.orderBy(order.equals(Order.ASC) ? cb.asc(root.get(orderField)) : cb.desc(root.get(orderField)));
    return query;
  }

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}
