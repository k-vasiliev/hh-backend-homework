package ru.hh.back.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.back.entity.NegotiationEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NegotiationDao {
    private final SessionFactory sessionFactory;
    public NegotiationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public List<NegotiationEntity> getVacancyNegotiation(Integer vacancyId) {
        try {
            List<NegotiationEntity> negotiations = getSessionFactory().getCurrentSession()
                    .createQuery("SELECT n From NegotiationEntity n WHERE n.vacancy.id = :vacId",
                            NegotiationEntity.class)
                    .setParameter("vacId", vacancyId)
                    .list();
            return negotiations;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Transactional
    public Integer save(NegotiationEntity negotiation) {
        getSessionFactory().getCurrentSession().saveOrUpdate(negotiation);
        return negotiation.getId();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
