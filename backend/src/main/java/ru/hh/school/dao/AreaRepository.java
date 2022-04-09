package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.school.domain.Area;

import javax.persistence.NoResultException;

@Repository
public class AreaRepository extends AbstractRepository<Area> {
    @Autowired
    public AreaRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Area getById(Long areaId) {
        try {
            Session session = getSessionFactory().getCurrentSession();
            return session.createQuery("SELECT a From Area a WHERE a.id = :id", Area.class)
                    .setParameter("id", areaId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}