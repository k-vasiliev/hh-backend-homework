package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.nab.entity.Negotiation;

import java.util.List;

@Repository
public class NegotiationDAO {

    private final SessionFactory sessionFactory;

    private final UserDAO userDAO;

    private final VacancyDAO vacancyDAO;

    public NegotiationDAO(SessionFactory sessionFactory, UserDAO userDAO, VacancyDAO vacancyDAO) {
        this.sessionFactory = sessionFactory;
        this.userDAO = userDAO;
        this.vacancyDAO = vacancyDAO;
    }

    public Negotiation createNegotiation(Negotiation negotiation) {
        sessionFactory.getCurrentSession().save(negotiation);
        return negotiation;
    }

    public Negotiation getNegotiationById(Integer id) {
        return sessionFactory.getCurrentSession().byId(Negotiation.class).load(id);
    }

    public List<Negotiation> getAllNegotiations() {
        return sessionFactory.getCurrentSession().createQuery(
                "from Negotiation where active = true", Negotiation.class)
                .getResultList();
    }

}
