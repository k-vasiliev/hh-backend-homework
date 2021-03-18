package ru.hh.school.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;

public abstract class FavoriteDao extends GenericDao {

  public static final Integer INCREMENT_VALUE = 1;

  public FavoriteDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  protected <T> CriteriaUpdate<T> getIncrementViewsCountCriteria(Class<T> clazz, List<Integer> ids) {
    CriteriaBuilder cb = getSession().getCriteriaBuilder();
    CriteriaUpdate<T> criteria = cb.createCriteriaUpdate(clazz);
    Root<T> root = criteria.from(clazz);
    Path<Integer> viewsCount = root.get("viewsCount");
    return criteria.set(viewsCount, cb.sum(viewsCount, INCREMENT_VALUE)).where(root.get("id").in(ids));
  }
}
