package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Employer;

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

    public Optional<Employer> get(int employerId) {
        try {
            Employer employer = session()
                    .getCurrentSession()
                    .createQuery("SELECT e FROM employer e WHERE e.id = :id", Employer.class)
                    .setParameter("id", employerId)
                    .getSingleResult();

            return Optional.of(employer);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public Employer create(Employer employer) {
        session().getCurrentSession().persist(employer);
        return employer;
    }

    public Employer saveOrUpdate(Employer employer) {
        session().getCurrentSession().saveOrUpdate(employer);
        return employer;
    }

    public void delete(Employer employer) {
        session().getCurrentSession().delete(employer);
    }

}
