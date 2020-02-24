package dao;

import models.Resume;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ResumeDao {
    private final SessionFactory sessionFactory;

    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveNew(Resume resume) {
        // TODO Implement
        session().persist(resume);
    }

    public Optional<Resume> getBy(int id) {
        // TODO Implement
        return Optional.ofNullable(
                session().get(Resume.class, id));
    }

    public Set<Resume> getActiveResumesForUserId(int userId) {
        // TODO Implement
        return new HashSet<>(
                session().createQuery("from Resume where isActive=true and userId=:userId", Resume.class)
                        .setParameter("userId",userId)
                        .list()
        );
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
