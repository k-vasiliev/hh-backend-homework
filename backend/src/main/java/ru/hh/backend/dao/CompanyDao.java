package ru.hh.backend.dao;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.backend.entity.Company;


import java.util.List;

@Repository
public class CompanyDao {
    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Company create(Company company) {
        sessionFactory.getCurrentSession().save(company);
        return company;
    }

    public Company get(Long id) {
        return sessionFactory.getCurrentSession().get(Company.class, id);
    }

    public List<Company> getAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Company c", Company.class)
                .getResultList();
    }

}
