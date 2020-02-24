package ru.hh.school.dao;

import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Company;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CompanyDao extends AbstractDao {

    public List<Company> getCompanies() {
        CriteriaQuery<Company> criteriaQuery = builder().createQuery(Company.class);
        Root<Company> root = criteriaQuery.from(Company.class);
        root.fetch("user");
        criteriaQuery.select(root);
        return session().createQuery(criteriaQuery).getResultList();
    }

    public Company getCompany(Integer companyId) {
        return session().get(Company.class, companyId);
    }
}
