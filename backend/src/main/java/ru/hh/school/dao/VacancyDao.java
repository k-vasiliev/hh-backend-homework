package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Vacancy;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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

    public Vacancy get(Integer id) {
        return session().get(Vacancy.class, id);
    }

    public List<Vacancy> getAll() {
        return session().createQuery("FROM Vacancy", Vacancy.class)
                .getResultList();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
