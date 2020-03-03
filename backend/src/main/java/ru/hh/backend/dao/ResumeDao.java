package ru.hh.backend.dao;

import org.hibernate.SessionFactory;
import ru.hh.backend.entity.Resume;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ResumeDao {

    private final SessionFactory sessionFactory;

    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Resume> getResume(Integer id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Resume.class, id));
    }

    public List<Resume> getResumes() {
        return sessionFactory.getCurrentSession().createQuery("from Resume", Resume.class).list();
    }

    public Integer save(Resume resume) {
        sessionFactory.getCurrentSession().save(resume);
        return resume.getResumeId();
    }
}
