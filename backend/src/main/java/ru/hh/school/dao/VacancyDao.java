package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.entity.Vacancy;

import java.util.List;
import java.util.Optional;

@Repository
public class VacancyDao {

    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Optional<Vacancy> getVacancy(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Vacancy.class, id));
    }

    @Transactional
    public Optional<List<Vacancy>> getVacancies(Integer page, Integer per_page) {

        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createQuery("from Vacancy vacancy", Vacancy.class)
                .setFirstResult(page)
                .setMaxResults(per_page)
                .getResultList());
    }

    @Transactional
    public void saveVacancy(Vacancy vacancy) {
        sessionFactory.getCurrentSession().saveOrUpdate(vacancy.getArea());
        sessionFactory.getCurrentSession().saveOrUpdate(vacancy.getEmployer());
        sessionFactory.getCurrentSession().saveOrUpdate(vacancy);
    }

    @Transactional
    public void deleteVacancy(Vacancy vacancy) {
        sessionFactory.getCurrentSession()
                .delete(vacancy);
    }

    @Transactional
    public Optional<Vacancy> editVacancy(Integer id, String comment) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("UPDATE Vacancy " +
                "SET comment = :comment " +
                "WHERE id = :id")
                .setParameter("comment", comment)
                .setParameter("id", id)
                .executeUpdate();

        return Optional.ofNullable(session.get(Vacancy.class, id));
    }

    @Transactional
    public void updateVacancyViews(int id) {
        sessionFactory.getCurrentSession()
                .createQuery("UPDATE Vacancy SET viewsCount = viewsCount + 1 WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
