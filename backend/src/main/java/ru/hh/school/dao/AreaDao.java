package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Area;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class AreaDao {

    private final SessionFactory sessionFactory;

    public AreaDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Area> getArea(Integer id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .get(Area.class, id));
    }

    public List<Area> getAreas() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Area", Area.class).list();
    }

    public Integer save(Area area) {
        sessionFactory.getCurrentSession().save(area);
        return area.getAreaId();
    }

    public Integer saveOrUpdate(Area area) {
        sessionFactory.getCurrentSession().saveOrUpdate(area);
        return area.getAreaId();
    }

    public Optional<Area> getAreaByAreaId(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Area where area_id =:id ", Area.class)
                .setParameter("id", id).uniqueResultOptional();
    }
}
