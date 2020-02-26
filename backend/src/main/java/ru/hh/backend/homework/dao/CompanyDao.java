package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.entity.CompanyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyDao {
    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CompanyEntity save(CompanyEntity companyEntity) {
        getSessionFactory().getCurrentSession().save(companyEntity);
        return companyEntity;
    }

    public Optional<CompanyEntity> get(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        try {
            CompanyEntity company = session
                    .createQuery("SELECT c FROM CompanyEntity c WHERE c.id = :id", CompanyEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(company);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
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
