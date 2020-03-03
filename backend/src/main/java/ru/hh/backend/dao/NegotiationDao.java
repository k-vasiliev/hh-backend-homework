package ru.hh.backend.dao;

import org.hibernate.SessionFactory;
import ru.hh.backend.entity.Negotiation;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class NegotiationDao {

    private final SessionFactory sessionFactory;

    public NegotiationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Negotiation> getNegotiation(Integer id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Negotiation.class, id));
    }

    public List<Negotiation> getNegotiationsByVacancyId(Integer vacancyId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select n from Negotiation n where n.vacancy.vacancyId = :vacancy_id", Negotiation.class)
                .setParameter("vacancy_id", vacancyId).list();
    }

    public Integer save(Negotiation negotiation) {
        sessionFactory.getCurrentSession().save(negotiation);
        return negotiation.getNegotiationId();
    }
}
