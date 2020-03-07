package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Vacancy;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class VacancyDao {

    private final SessionFactory sessionFactory;
    @Inject
    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Vacancy create(Vacancy vacancy) {
        session().save(vacancy);
        return vacancy;
    }

    public Optional<Vacancy> get(Integer id) {
        Vacancy vacancy = session().createQuery("FROM Vacancy WHERE id = :id", Vacancy.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(vacancy);
    }

    public List<Vacancy> getAll() {
        return session().createQuery("FROM Vacancy", Vacancy.class)
                .getResultList();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
