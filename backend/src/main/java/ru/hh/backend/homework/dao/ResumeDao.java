package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.backend.homework.entity.ResumeEntity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ResumeDao {
    private final SessionFactory sessionFactory;

    @Inject
    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ResumeEntity save(ResumeEntity resumeEntity) {
        sessionFactory.getCurrentSession().save(resumeEntity);
        return resumeEntity;
    }

    public ResumeEntity get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("SELECT r FROM ResumeEntity r WHERE r.id = :id", ResumeEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<ResumeEntity> getAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("SELECT r FROM ResumeEntity r", ResumeEntity.class)
                .getResultList();
    }
}
