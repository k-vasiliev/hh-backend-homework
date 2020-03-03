package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.nab.entity.Company;
import ru.hh.nab.entity.User;
import ru.hh.nab.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CompanyDAO {

    private final SessionFactory sessionFactory;

    private final UserService userService;

    public CompanyDAO(SessionFactory sessionFactory, UserService userService) {
        this.sessionFactory = sessionFactory;
        this.userService = userService;
    }

    public Company addCompany(int userId, String name) {
        User user = userService.getUsersById(userId);
        Company company = new Company(user, name, LocalDate.now(), true);
        sessionFactory.getCurrentSession().save(company);
        return company;
    }

    public List<Company> getAllCompany() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Company where active = true", Company.class)
                .getResultList();
    }
    
    public Company getCompanyById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Company where company_id = :paramId and active = true", Company.class)
                .setParameter("paramId", id)
                .getSingleResult();
    }
}
