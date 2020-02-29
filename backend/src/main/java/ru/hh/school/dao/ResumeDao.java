package ru.hh.school.dao;

import ru.hh.school.entity.Resume;

import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Singleton
public class ResumeDao extends AbstractDao {

    public List<Resume> getAllResumes() {
        CriteriaQuery<Resume> criteriaQuery = builder().createQuery(Resume.class);
        Root<Resume> root = criteriaQuery.from(Resume.class);
        root.fetch("user");
        criteriaQuery.select(root);
        return session().createQuery(criteriaQuery).getResultList();
    }

    public Resume getResume(Integer resumeId) {
        return session().get(Resume.class, resumeId);
    }
}
