package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Company;
import ru.hh.school.entity.Negotiation;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class NegotiationDao {

    private final SessionFactory sessionFactory;
    @Inject
    public NegotiationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Negotiation create(Negotiation negotiation) {
        session().save(negotiation);
        return negotiation;
    }

    public List<Negotiation> getByVacancyId(Integer vacancyId) {
        return session().createQuery("FROM Negotiation WHERE vacancy.id = :vacancyId", Negotiation.class)
                .setParameter("vacancyId", vacancyId)
                .getResultList();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

}
