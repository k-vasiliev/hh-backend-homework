package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.backend.homework.entity.VacancyEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class VacancyDao {
    private final SessionFactory sessionFactory;

    @Inject
    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public VacancyEntity save(VacancyEntity vacancyEntity) {
        sessionFactory.getCurrentSession().save(vacancyEntity);
        return vacancyEntity;
    }

    public VacancyEntity get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        VacancyEntity vacancy = session
                .createQuery("SELECT v FROM VacancyEntity v WHERE v.id = :id", VacancyEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(vacancy).orElseGet(null);
    }

    public List<VacancyEntity> getAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("SELECT v FROM VacancyEntity v", VacancyEntity.class)
                .getResultList();
    }
}
