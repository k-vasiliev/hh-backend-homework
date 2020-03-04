package ru.hh.back.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.back.entity.ResumeEntity;

import java.util.List;

@Repository
public class ResumeDao {
    private final SessionFactory sessionFactory;

    public List<ResumeEntity> getResume() {
        List<ResumeEntity> resumes = getSessionFactory().getCurrentSession()
                .createQuery("From ResumeEntity", ResumeEntity.class)
                .list();
        return resumes;

    }

    public Integer save(ResumeEntity resumeEntity) {
        getSessionFactory().getCurrentSession().saveOrUpdate(resumeEntity);
        return resumeEntity.getId();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
