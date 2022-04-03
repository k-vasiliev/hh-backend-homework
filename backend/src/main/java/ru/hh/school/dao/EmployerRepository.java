package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.school.domain.Employer;
import ru.hh.school.domain.Vacancy;

import java.util.Optional;

@Repository
public class EmployerRepository extends AbstractRepository<Employer> {
    @Autowired
    public EmployerRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Employer> getById(Long employerId) {
        Session session = getSessionFactory().getCurrentSession();
        Employer employer = session.createQuery("SELECT u From Vacancy u WHERE u.id = :id", Employer.class)
                .setParameter("id", employerId)
                .getSingleResult();
        return Optional.of(employer);
    }
}