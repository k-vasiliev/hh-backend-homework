package ru.hh.school.dao;

import org.hibernate.SessionFactory;

import ru.hh.school.entity.Area;

public class AreaDao extends GenericDao {

  public AreaDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public void save(Area area) {
    super.save(area);
  }

  public Area get(Integer id) {
    return super.get(Area.class, id);
  }

  public void update(Area area) {
    if (area == null)
      return;
    getSession().update(area);
  }

  public void delete(Area area) {
    if (area == null)
      return;
    getSession().delete(area);
  }
}
