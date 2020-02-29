package ru.hh.school.dao;

import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Singleton
public class VacancyDao extends AbstractDao {

    public List<Vacancy> getAllVacancies() {
        CriteriaQuery<Vacancy> criteriaQuery = builder().createQuery(Vacancy.class);
        Root<Vacancy> root = criteriaQuery.from(Vacancy.class);
        root.fetch("company");
        criteriaQuery.select(root);
        return session().createQuery(criteriaQuery).getResultList();
    }

    public Vacancy getVacancy(Integer vacancyId) {
        return session().get(Vacancy.class, vacancyId);
    }

}
