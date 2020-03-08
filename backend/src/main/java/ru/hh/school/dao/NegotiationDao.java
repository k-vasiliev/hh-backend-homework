package ru.hh.school.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.NegotiationEntity;


import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;


@Singleton
public class NegotiationDao {


    private final SessionFactory sessionFactory;

    public NegotiationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public NegotiationEntity create(NegotiationEntity negotiationEntity) {
        sessionFactory.getCurrentSession().save(negotiationEntity);
        return negotiationEntity;
    }


    public List<NegotiationEntity> getAllVacancyByResumeId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        List<NegotiationEntity> negotiationEntityList = null;
        try {
            negotiationEntityList = session
                    .createQuery("SELECT n FROM Negotiation n WHERE n.resume = :id", NegotiationEntity.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return negotiationEntityList;
    }


    public List<NegotiationEntity> getAllResumeByVacancyId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        List<NegotiationEntity> negotiationEntityList = null;
        try {
            negotiationEntityList = session
                    .createQuery("SELECT n FROM Negotiation n WHERE n.vacancy = :id", NegotiationEntity.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return negotiationEntityList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}