package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.school.domain.Employer;
import ru.hh.school.domain.Vacancy;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class EmployerRepository extends AbstractRepository<Employer> {
    @Autowired
    public EmployerRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Employer getById(Long employerId) {
        try {
            Session session = getSessionFactory().getCurrentSession();
            return session.createQuery("SELECT u From Employer u WHERE u.id = :id", Employer.class)
                    .setParameter("id", employerId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}