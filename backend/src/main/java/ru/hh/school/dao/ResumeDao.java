package ru.hh.school.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.CompanyEntity;
import ru.hh.school.entity.NegotiationEntity;
import ru.hh.school.entity.ResumeEntity;


import javax.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ResumeDao {
    private final SessionFactory sessionFactory;

    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public ResumeEntity create(ResumeEntity resumeEntity) {
        sessionFactory.getCurrentSession().save(resumeEntity);
        return resumeEntity;
    }

    public ResumeEntity get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        ResumeEntity resumeEntity = null;
        try {
            resumeEntity = session.get(ResumeEntity.class,id);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return resumeEntity;
    }

    public List<ResumeEntity> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Resume", ResumeEntity.class)
                .getResultList();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
