package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class VacancyDao {

    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory session() {
        return sessionFactory;
    }

    public Optional<Vacancy> get(Integer vacancyId) {
        return Optional.ofNullable(session().getCurrentSession().get(Vacancy.class, vacancyId));
    }

    public Vacancy create(Vacancy vacancy) {
        session().getCurrentSession().persist(vacancy);
        return vacancy;
    }

    public Vacancy saveOrUpdate(Vacancy vacancy) {
        session().getCurrentSession().saveOrUpdate(vacancy);
        return vacancy;
    }

    public void delete(Vacancy vacancy) {
        session().getCurrentSession().delete(vacancy);
    }

}
