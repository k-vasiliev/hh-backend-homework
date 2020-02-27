package ru.hh.school.dao;

import org.springframework.stereotype.Repository;
import ru.hh.school.models.Resume;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ResumeDao {
    private final SessionFactory sessionFactory;

    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Resume create(Resume resume) {
        session().persist(resume);
        return resume;
    }

    @Transactional
    public Resume get(Integer id) {
        return session().get(Resume.class, id);
    }

    @Transactional
    public List<Resume> getByUserId(Integer userId) {
        return session().createQuery("FROM Resume WHERE userId=:userId", Resume.class)
                        .setParameter("userId", userId)
                        .getResultList();
    }

    @Transactional
    public List<Resume> getAll() {
        return session().createQuery("FROM Resume", Resume.class)
                .getResultList();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
