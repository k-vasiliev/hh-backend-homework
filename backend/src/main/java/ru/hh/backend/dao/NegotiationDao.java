package ru.hh.backend.dao;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.model.Negotiation;

import java.util.List;

@Repository
public class NegotiationDao {
    private final SessionFactory sessionFactory;

    public NegotiationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Negotiation create(Negotiation negotiation) {
        sessionFactory.getCurrentSession().save(negotiation);
        return negotiation;
    }

    @Transactional
    public List<Negotiation> getAllByVacancyId(Long vacancyId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM  Negotiation n WHERE n.vacancy.id = :vacancyId", Negotiation.class)
                .setParameter("vacancyId", vacancyId)
                .getResultList();
    }

}
