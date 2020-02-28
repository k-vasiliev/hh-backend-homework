package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.entity.Company;
import ru.hh.nab.entity.Vacancy;

import java.util.Date;
import java.util.List;

@Repository
public class VacancyDAO {

    private final SessionFactory sessionFactory;

    private final CompanyDAO companyDAO;

    public VacancyDAO(SessionFactory sessionFactory, CompanyDAO companyDAO) {
        this.sessionFactory = sessionFactory;
        this.companyDAO = companyDAO;
    }

    @Transactional
    public Vacancy addVacancy(int companyId, String title,
                              String salary, String description, String contacts) {
        Company company = companyDAO.getCompanyById(companyId);
        Vacancy vacancy = new Vacancy(company, title, Integer.valueOf(salary),
                description, contacts, true, new Date());
        sessionFactory.getCurrentSession().save(vacancy);
        return vacancy;
    }

    @Transactional
    public List<Vacancy> getAllVacancy() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Vacancy where active = true", Vacancy.class).getResultList();
    }
}
