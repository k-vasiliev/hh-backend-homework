package ru.hh.school.dao;

import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Resume;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ResumeDao extends AbstractDao {

    public List<Resume> getAllResumes() {
        CriteriaQuery<Resume> criteriaQuery = builder().createQuery(Resume.class);
        Root<Resume> root = criteriaQuery.from(Resume.class);
        root.fetch("user");
        criteriaQuery.select(root);
        return session().createQuery(criteriaQuery).getResultList();
    }
}
