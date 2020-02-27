package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.models.Company;

import java.util.List;

@Repository
public class CompanyDao {
    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Company create(Company company) {
        session().save(company);
        return company;
    }

    @Transactional
    public Company get(Integer id) {
        return session().get(Company.class, id);
    }

    @Transactional
    public List<Company> getAll() {
        return session().createQuery("FROM Company", Company.class)
                .getResultList();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

}
