package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.entity.Company;
import ru.hh.nab.entity.Users;

import java.util.Date;
import java.util.List;

@Repository
public class CompanyDAO {

    private final SessionFactory sessionFactory;

    private final UserDAO userDAO;

    public CompanyDAO(SessionFactory sessionFactory, UserDAO userDAO) {
        this.sessionFactory = sessionFactory;
        this.userDAO = userDAO;
    }

    @Transactional
    public Company addCompany(int userId, String name) {
        Users user = userDAO.getUsersById(userId);
        Company company = new Company(user, name, new Date(), true);
        sessionFactory.getCurrentSession().save(company);
        return company;
    }

    @Transactional
    public List<Company> getAllCompany() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Company where active = true", Company.class)
                .getResultList();
    }
    
    @Transactional
    public Company getCompanyById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Company where company_id = :paramId and active = true", Company.class)
                .setParameter("paramId", id)
                .getSingleResult();
    }
}
