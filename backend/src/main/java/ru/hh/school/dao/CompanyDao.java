package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.models.Company;

import javax.transaction.Transactional;

public class CompanyDao {
    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void create(Company company) {
        session().save(company);
    }

    @Transactional
    public Company get(Integer id) {
        return session().get(Company.class, id);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

}
