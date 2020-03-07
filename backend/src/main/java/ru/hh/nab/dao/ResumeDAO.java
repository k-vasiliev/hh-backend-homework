package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.nab.dto.ResponseResumeDTO;
import ru.hh.nab.entity.Resume;

import java.util.List;

@Repository
public class ResumeDAO {

    private final SessionFactory sessionFactory;

    public ResumeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Resume addResume(Resume resume) {
        sessionFactory.getCurrentSession().save(resume);
        return resume;
    }

    public List<ResponseResumeDTO> getAllResume() {
        return sessionFactory.getCurrentSession()
                .createQuery(
                        "select new ru.hh.nab.dto.ResponseResumeDTO(res.heading, user.userName, res.lastUpdate, res.resumeId) " +
                                "from Resume res " +
                                "join res.users user where res.active = true"
                        , ResponseResumeDTO.class).getResultList();
    }

    public ResponseResumeDTO getResponseResumeById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery(
                        "select new ru.hh.nab.dto.ResponseResumeDTO(res.heading, user.userName, res.lastUpdate, res.resumeId) " +
                                "from Resume res " +
                                "join res.users user where res.resumeId = :paramId and res.active = true"
                        , ResponseResumeDTO.class).setParameter("paramId", id).getSingleResult();
    }

    public Resume getResumeById(Integer id) {
        return sessionFactory.getCurrentSession().byId(Resume.class).load(id);
    }
}
