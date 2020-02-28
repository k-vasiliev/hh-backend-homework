package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.hh.backend.homework.entity.NegotiationEntity;

import javax.inject.Singleton;
import java.util.List;

@Repository
public class NegotiationDao {
    private final SessionFactory sessionFactory;
    private final VacancyDao vacancyDao;

    @Autowired
    public NegotiationDao(SessionFactory sessionFactory, VacancyDao vacancyDao) {
        this.sessionFactory = sessionFactory;
        this.vacancyDao = vacancyDao;
    }

    public NegotiationEntity save(NegotiationEntity negotiationEntity) {
        getSession().save(negotiationEntity);
        return negotiationEntity;
    }

    public List<NegotiationEntity> getAllByVacancy(Long vacancyId) {
        return getSession()
                .createQuery("SELECT n FROM NegotiationEntity n WHERE n.vacancy = :vacancy"
                        , NegotiationEntity.class)
                .setParameter("vacancy", vacancyDao.get(vacancyId))
                .list();
    }

    private Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (Exception e) {
            return sessionFactory.openSession();
        }
    }
}
