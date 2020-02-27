package ru.hh.backend.dao;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.backend.entity.Vacancy;


import java.util.List;

@Repository
public class VacancyDao {
    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Vacancy create(Vacancy vacancy) {
        sessionFactory.getCurrentSession().save(vacancy);
        return vacancy;
    }

    public Vacancy get(Long id) {
        return sessionFactory.getCurrentSession().get(Vacancy.class, id);
    }

    public List<Vacancy> getAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Vacancy v", Vacancy.class)
                .getResultList();
    }

}
