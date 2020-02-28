package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.entity.Resume;
import ru.hh.nab.entity.Users;

import java.util.Date;
import java.util.List;

@Repository
public class ResumeDAO {

    private final SessionFactory sessionFactory;

    private final UserDAO userDAO;

    public ResumeDAO(SessionFactory sessionFactory, UserDAO userDAO) {
        this.sessionFactory = sessionFactory;
        this.userDAO = userDAO;
    }

    @Transactional
    public Resume addResume(int userId, String exp, String heading, String contacts) {
        Users user = userDAO.getUsersById(userId);
        Resume resume = new Resume(user, exp, heading, contacts, true, new Date());
        sessionFactory.getCurrentSession().save(resume);
        return resume;
    }

    @Transactional
    public List<Resume> getAllResume() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Resume where active = true", Resume.class).getResultList();
    }
}
