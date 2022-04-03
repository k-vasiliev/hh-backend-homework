package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.school.domain.Favourite;
import ru.hh.school.domain.Vacancy;

import java.util.Optional;

@Repository
public class VacancyRepository extends AbstractRepository<Vacancy> {
    @Autowired
    public VacancyRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Vacancy> getById(Long vacancyId) {
        Session session = getSessionFactory().getCurrentSession();
        Vacancy vacancy = session.createQuery("SELECT u From Vacancy u WHERE u.id = :id", Vacancy.class)
                .setParameter("id", vacancyId)
                .getSingleResult();
        return Optional.of(vacancy);
    }
}