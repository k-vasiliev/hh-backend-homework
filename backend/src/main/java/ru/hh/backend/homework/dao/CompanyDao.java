package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.hh.backend.homework.entity.CompanyEntity;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CompanyEntity save(CompanyEntity companyEntity) {
        getSession().save(companyEntity);
        return companyEntity;
    }

    public CompanyEntity get(Long id) {
        Session session = getSession();
        CompanyEntity company = session
                .createQuery("SELECT c FROM CompanyEntity c WHERE c.id = :id", CompanyEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return company;
    }

    public List<CompanyEntity> getAll() {
        return getSession()
                .createQuery("SELECT c FROM CompanyEntity c", CompanyEntity.class)
                .list();
    }

    private Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (Exception e) {
            return sessionFactory.openSession();
        }
    }
}
