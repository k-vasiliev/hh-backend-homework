package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Resume;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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

    public Resume get(Integer id) {
        return session().get(Resume.class, id);
    }

    public List<Resume> getByUserId(Integer userId) {
        return session().createQuery("FROM Resume WHERE userId=:userId", Resume.class)
                        .setParameter("userId", userId)
                        .getResultList();
    }

    public List<Resume> getAll() {
        return session().createQuery("FROM Resume", Resume.class)
                .getResultList();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
