package ru.hh.backend.dao;

import org.hibernate.SessionFactory;
import ru.hh.backend.entity.Company;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class CompanyDao {

    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Company> getCompany(Integer id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Company.class, id));
    }

    public List<Company> getCompanies() {
        return sessionFactory.getCurrentSession().createQuery("from Company", Company.class).list();
    }

    public Integer save(Company company) {
        sessionFactory.getCurrentSession().save(company);
        return company.getCompanyId();
    }


}
