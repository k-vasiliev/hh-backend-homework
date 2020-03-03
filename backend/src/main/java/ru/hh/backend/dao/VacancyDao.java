package ru.hh.backend.dao;

import org.hibernate.SessionFactory;
import ru.hh.backend.entity.Vacancy;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class VacancyDao {

    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Vacancy> getVacancy(Integer id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Vacancy.class, id));
    }

    public List<Vacancy> getVacancies() {
        return sessionFactory.getCurrentSession().createQuery("from Vacancy", Vacancy.class).list();
    }

    public Integer save(Vacancy vacancy) {
        sessionFactory.getCurrentSession().save(vacancy);
        return vacancy.getVacancyId();
    }
}
