package ru.hh.backend.homework.dao;

import org.hibernate.SessionFactory;
import ru.hh.backend.homework.entity.NegotiationEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class NegotiationDao {
    private final SessionFactory sessionFactory;

    @Inject
    public NegotiationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public NegotiationEntity save(NegotiationEntity negotiationEntity) {
        sessionFactory.getCurrentSession().save(negotiationEntity);
        return negotiationEntity;
    }

    public List<NegotiationEntity> getAllByVacancy(Integer vacancyId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("SELECT n FROM NegotiationEntity n WHERE n.vacancy_id = :vacancyId"
                        , NegotiationEntity.class)
                .setParameter("vacancyId", vacancyId)
                .getResultList();
    }
}
