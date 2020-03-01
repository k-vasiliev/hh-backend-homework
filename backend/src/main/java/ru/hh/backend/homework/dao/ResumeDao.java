package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.backend.homework.entity.ResumeEntity;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ResumeDao {
    private final SessionFactory sessionFactory;

    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ResumeEntity save(ResumeEntity resumeEntity) {
        getSessionFactory().getCurrentSession().save(resumeEntity);
        return resumeEntity;
    }

    public ResumeEntity get(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        ResumeEntity resume = session
                .createQuery("SELECT r FROM ResumeEntity r WHERE r.id = :id", ResumeEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(resume).orElseGet(null);
    }

    public List<ResumeEntity> getAll() {
        return getSessionFactory()
                .getCurrentSession()
                .createQuery("SELECT r FROM ResumeEntity r", ResumeEntity.class)
                .getResultList();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
