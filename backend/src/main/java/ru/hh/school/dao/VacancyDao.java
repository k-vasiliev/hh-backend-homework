package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.models.Vacancy;

import javax.transaction.Transactional;

public class VacancyDao {
    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void create(Vacancy vacancy) {
        session().save(vacancy);
    }

    @Transactional
    public Vacancy get(Integer id) {
        return session().get(Vacancy.class, id);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
