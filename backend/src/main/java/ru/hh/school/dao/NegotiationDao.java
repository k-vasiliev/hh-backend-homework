package ru.hh.school.dao;

import ru.hh.school.entity.Negotiation;

import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Singleton
public class NegotiationDao extends AbstractDao {

    public List<Negotiation> getNegotiationsByVacancyId(Integer vacancyId) {
        CriteriaQuery<Negotiation> criteriaQuery = builder().createQuery(Negotiation.class);
        Root<Negotiation> root = criteriaQuery.from(Negotiation.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder().equal(root.get("vacancy").get("vacancyId"),vacancyId));
        return session().createQuery(criteriaQuery).getResultList();
    }
}
