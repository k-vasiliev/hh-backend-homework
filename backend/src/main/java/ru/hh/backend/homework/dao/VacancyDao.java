package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.backend.homework.entity.VacancyEntity;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class VacancyDao {
    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public VacancyEntity save(VacancyEntity vacancyEntity) {
        getSessionFactory().getCurrentSession().save(vacancyEntity);
        return vacancyEntity;
    }

    public VacancyEntity get(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        VacancyEntity vacancy = session
                .createQuery("SELECT v FROM VacancyEntity v WHERE v.id = :id", VacancyEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(vacancy).orElseGet(null);
    }

    public List<VacancyEntity> getAll() {
        return getSessionFactory()
                .getCurrentSession()
                .createQuery("SELECT v FROM VacancyEntity v", VacancyEntity.class)
                .getResultList();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
