package ru.hh.school.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import ru.hh.school.entity.FavoriteEmployer;

public class FavoriteEmployerDao extends FavoriteDao {

  @Inject
  public FavoriteEmployerDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public void save(FavoriteEmployer employer) {
    super.save(employer);
  }

  public FavoriteEmployer get(Integer id) {
    return super.get(FavoriteEmployer.class, id);
  }

  public void update(FavoriteEmployer employer) {
    super.update(employer);
  }

  public void delete(FavoriteEmployer employer) {
    super.delete(employer);
  }

  public List<FavoriteEmployer> get(Integer startIdx, Integer count) {
    List<FavoriteEmployer> results = getSession()
      .createQuery(getOrderbyCriteria(FavoriteEmployer.class, Order.ASC, "name", "area"))
      .setFirstResult(startIdx).setMaxResults(count).getResultList();
    return results;
  }

  public void incrementViewsCount(List<Integer> ids) {
    if (ids.isEmpty())
      return;
    getSession().createQuery(getIncrementViewsCountCriteria(FavoriteEmployer.class, ids)).executeUpdate();
  }
}
