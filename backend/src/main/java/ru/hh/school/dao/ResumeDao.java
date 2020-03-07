package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Resume;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class ResumeDao {

    private final SessionFactory sessionFactory;
    @Inject
    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Resume create(Resume resume) {
        session().persist(resume);
        return resume;
    }

    public Optional<Resume> get(Integer id) {
        Resume resume = session().createQuery("FROM Resume WHERE id = :id", Resume.class)
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(resume);
    }

    public List<Resume> getAll() {
        return session().createQuery("FROM Resume", Resume.class)
                .getResultList();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
