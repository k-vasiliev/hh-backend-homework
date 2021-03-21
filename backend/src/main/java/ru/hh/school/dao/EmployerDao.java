package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.entity.Employer;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployerDao {

    private final SessionFactory sessionFactory;

    public EmployerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Optional<Employer> getEmployer(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Employer.class, id));

    }

    @Transactional
    public Optional<List<Employer>> getEmployers(Integer page, Integer per_page) {

        return Optional.ofNullable(sessionFactory.getCurrentSession()
        .createQuery("from Employer employer", Employer.class)
                .setFirstResult(page)
                .setMaxResults(per_page)
                .getResultList());
    }

    @Transactional
    public void saveEmployer(Employer employer) {
        sessionFactory.getCurrentSession().saveOrUpdate(employer.getArea());
        sessionFactory.getCurrentSession().saveOrUpdate(employer);
    }

    @Transactional
    public void deleteEmployer(Employer employer) {
        sessionFactory.getCurrentSession()
                .delete(employer);
    }

    @Transactional
    public Optional<Employer> editEmployer(Integer id, String comment) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("UPDATE Employer " +
                "SET comment = :comment " +
                "WHERE id = :id")
                .setParameter("comment", comment)
                .setParameter("id", id)
                .executeUpdate();

        return Optional.ofNullable(session.get(Employer.class, id));
    }

    @Transactional
    public void updateEmployerViews(int id) {
        sessionFactory.getCurrentSession()
                .createQuery("UPDATE Employer SET viewsCount = viewsCount + 1 WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
