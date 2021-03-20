package ru.hh.school.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import ru.hh.school.entity.FavoriteVacancy;

public class FavoriteVacancyDao extends FavoriteDao {

  @Inject
  public FavoriteVacancyDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public void save(FavoriteVacancy vacancy) {
    super.save(vacancy);
  }

  public FavoriteVacancy get(Integer id) {
    return super.get(FavoriteVacancy.class, id);
  }

  public void update(FavoriteVacancy vacancy) {
    super.update(vacancy);
  }

  public void delete(FavoriteVacancy vacancy) {
    super.delete(vacancy);
  }

  public List<FavoriteVacancy> get(Integer startIdx, Integer count) {
    List<FavoriteVacancy> results = getSession()
      .createQuery(getOrderbyCriteria(FavoriteVacancy.class, Order.ASC, "name", "area", "employer"))
      .setFirstResult(startIdx).setMaxResults(count).getResultList();
    return results;
  }

  public void incrementViewsCount(List<Integer> ids) {
    if (ids.isEmpty())
      return;
    getSession().createQuery(getIncrementViewsCountCriteria(FavoriteVacancy.class, ids)).executeUpdate();
  }
}
