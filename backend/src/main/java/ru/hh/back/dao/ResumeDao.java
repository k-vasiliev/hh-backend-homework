package ru.hh.back.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.back.entity.ResumeEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ResumeDao {
    private final SessionFactory sessionFactory;

    @Transactional
    public List<ResumeEntity> getResume() {
        try {
            List<ResumeEntity> resumes = getSessionFactory().getCurrentSession()
                    .createQuery("SELECT r From ResumeEntity", ResumeEntity.class)
                    .list();
            return resumes;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Transactional
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
