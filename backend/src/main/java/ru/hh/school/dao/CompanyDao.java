package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Company;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class CompanyDao {

    private final SessionFactory sessionFactory;
    @Inject
    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Company create(Company company) {
        session().save(company);
        return company;
    }

    public Company get(Integer id) {
        return session().get(Company.class, id);
    }

    public List<Company> getAll() {
        return session().createQuery("FROM Company", Company.class)
                .getResultList();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

}
