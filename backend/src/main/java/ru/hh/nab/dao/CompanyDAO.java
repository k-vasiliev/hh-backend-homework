package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.nab.dto.ResponseCompanyDTO;
import ru.hh.nab.entity.Company;

import java.util.List;

@Repository
public class CompanyDAO {

    private final SessionFactory sessionFactory;

    public CompanyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Company addCompany(Company company) {
        sessionFactory.getCurrentSession().save(company);
        return company;
    }

    public List<ResponseCompanyDTO> getAllCompany() {
        return sessionFactory.getCurrentSession()
                .createQuery("select new ru.hh.nab.dto.ResponseCompanyDTO(comp.name, comp.companyId) " +
                        "from Company as comp where comp.active = true", ResponseCompanyDTO.class)
                .getResultList();
    }
    
    public Company getCompanyById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Company where companyId = :paramId and active = true", Company.class)
                .setParameter("paramId", id)
                .getSingleResult();
    }
}
