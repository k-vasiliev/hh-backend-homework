package ru.hh.backend.dao;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.model.Vacancy;

import java.util.List;

@Repository
public class VacancyDao {
    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Vacancy create(Vacancy vacancy) {
        sessionFactory.getCurrentSession().save(vacancy);
        return vacancy;
    }

    @Transactional
    public Vacancy get(Long id) {
        return sessionFactory.getCurrentSession().get(Vacancy.class, id);
    }

    @Transactional
    public List<Vacancy> getAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Vacancy v", Vacancy.class)
                .getResultList();
    }

}
