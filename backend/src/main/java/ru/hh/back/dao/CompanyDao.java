package ru.hh.back.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.back.entity.CompanyEntity;

import java.util.List;

@Repository
public class CompanyDao {
    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<CompanyEntity> getCompany() {
        List<CompanyEntity> companies = getSessionFactory().getCurrentSession()
                .createQuery("From CompanyEntity", CompanyEntity.class)
                .list();
        return companies;

    }

    @Transactional
    public Integer save(CompanyEntity company) {
        getSessionFactory().getCurrentSession().saveOrUpdate(company);
        return company.getId();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
