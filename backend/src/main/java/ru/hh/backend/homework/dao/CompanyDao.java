package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.backend.homework.entity.CompanyEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class CompanyDao {
    private final SessionFactory sessionFactory;

    @Inject
    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CompanyEntity save(CompanyEntity companyEntity) {
        getSessionFactory().getCurrentSession().save(companyEntity);
        return companyEntity;
    }

    public CompanyEntity get(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        CompanyEntity company = session
                .createQuery("SELECT c FROM CompanyEntity c WHERE c.id = :id", CompanyEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(company).orElseGet(null);
    }

    public List<CompanyEntity> getAll() {
        return getSessionFactory()
                .getCurrentSession()
                .createQuery("SELECT c FROM CompanyEntity c", CompanyEntity.class)
                .getResultList();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
