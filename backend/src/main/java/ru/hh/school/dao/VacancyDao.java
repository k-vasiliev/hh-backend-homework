package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.models.Vacancy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class VacancyDao {
    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Vacancy create(Vacancy vacancy) {
        session().save(vacancy);
        return vacancy;
    }

    @Transactional
    public Vacancy get(Integer id) {
        return session().get(Vacancy.class, id);
    }

    @Transactional
    public List<Vacancy> getByCompanyId(Integer companyId) {
        return session().createQuery("FROM Vacancy WHERE companyId=:companyId", Vacancy.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Transactional
    public List<Vacancy> getAll() {
        return session().createQuery("FROM Vacancy", Vacancy.class)
                .getResultList();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
