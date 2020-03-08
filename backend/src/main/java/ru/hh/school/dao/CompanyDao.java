package ru.hh.school.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.CompanyEntity;


import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class CompanyDao {
    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CompanyEntity create(CompanyEntity companyEntity) {
        getSessionFactory().getCurrentSession().save(companyEntity);
        return companyEntity;
    }

    public CompanyEntity get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CompanyEntity companyEntity = null;
        try {
            companyEntity = session.get(CompanyEntity.class,id);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return companyEntity;
    }

    public List<CompanyEntity> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<CompanyEntity> companyEntityList = null;
        try {
            companyEntityList = session
                    .createQuery("FROM CompanyEntity", CompanyEntity.class)
                    .getResultList();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return companyEntityList;
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}