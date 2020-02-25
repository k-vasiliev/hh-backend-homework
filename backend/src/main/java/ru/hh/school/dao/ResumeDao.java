package ru.hh.school.dao;

import ru.hh.school.models.Resume;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ResumeDao {
    private final SessionFactory sessionFactory;

    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void create(Resume resume) {
        session().persist(resume);
    }

    @Transactional
    public Optional<Resume> getBy(int id) {
        return Optional.ofNullable(
                session().get(Resume.class, id));
    }

    @Transactional
    public Set<Resume> getResumesForUserId(int userId) {
        return new HashSet<>(
                session().createQuery("from Resume where userId=:userId", Resume.class)
                        .setParameter("userId",userId)
                        .list()
        );
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
