package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.hh.backend.homework.entity.ResumeEntity;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Repository
public class ResumeDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ResumeEntity save(ResumeEntity resumeEntity) {
        getSession().save(resumeEntity);
        return resumeEntity;
    }

    public ResumeEntity get(Long id) {
        Session session = getSession();

        ResumeEntity resume = session
                .createQuery("SELECT r FROM ResumeEntity r WHERE r.id = :id", ResumeEntity.class)
                .setParameter("id", id)
                .getSingleResult();
        return resume;
    }

    public List<ResumeEntity> getAll() {
        return getSession()
                .createQuery("SELECT r FROM ResumeEntity r", ResumeEntity.class)
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
