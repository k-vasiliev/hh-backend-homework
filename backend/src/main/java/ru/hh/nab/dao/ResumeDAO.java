package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.nab.dto.ResponseResumeDTO;
import ru.hh.nab.entity.Resume;
import ru.hh.nab.entity.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ResumeDAO {

    private final SessionFactory sessionFactory;

    private final UserDAO userDAO;

    public ResumeDAO(SessionFactory sessionFactory, UserDAO userDAO) {
        this.sessionFactory = sessionFactory;
        this.userDAO = userDAO;
    }

    public Resume addResume(int userId, String exp, String heading, String contacts) {
        User user = userDAO.getUsersById(userId);
        Resume resume = new Resume(user, exp, heading, contacts, true, LocalDate.now());
        sessionFactory.getCurrentSession().save(resume);
        return resume;
    }

    public List<ResponseResumeDTO> getAllResume() {
        return sessionFactory.getCurrentSession()
                .createQuery(
                        "select new ru.hh.nab.dto.ResponseResumeDTO(res.heading, user.userName, res.lastUpdate, res.resId) " +
                                "from Resume res " +
                                "join res.users user where res.active = true"
                        , ResponseResumeDTO.class).getResultList();
    }
}
