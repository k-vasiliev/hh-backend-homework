package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.hh.backend.homework.entity.VacancyEntity;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Repository
public class VacancyDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public VacancyEntity save(VacancyEntity vacancyEntity) {
        getSession().save(vacancyEntity);
        return vacancyEntity;
    }

    public VacancyEntity get(Long id) {
        Session session = getSession();
        VacancyEntity vacancy = session
                .createQuery("SELECT v FROM VacancyEntity v WHERE v.id = :id", VacancyEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return vacancy;
    }

    public List<VacancyEntity> getAll() {
        return getSession()
                .createQuery("SELECT v FROM VacancyEntity v", VacancyEntity.class)
                .list();
    }

    private Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (Exception e) {
            return sessionFactory.openSession();
        }
    }
}
