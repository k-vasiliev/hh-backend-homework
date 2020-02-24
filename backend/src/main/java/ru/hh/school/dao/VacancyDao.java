package ru.hh.school.dao;

import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Vacancy;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class VacancyDao extends AbstractDao {

    public List<Vacancy> getAllVacancies() {
        CriteriaQuery<Vacancy> criteriaQuery = builder().createQuery(Vacancy.class);
        Root<Vacancy> root = criteriaQuery.from(Vacancy.class);
        root.fetch("company");
        criteriaQuery.select(root);
        return session().createQuery(criteriaQuery).getResultList();
    }

}
