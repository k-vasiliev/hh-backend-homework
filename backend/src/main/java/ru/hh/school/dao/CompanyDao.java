package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Company;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class CompanyDao {

    private final SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Company> getCompany(Integer id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Company.class, id));
    }

    public Optional<Company> getCompanyByCompanyId(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Company where companyId =:id ", Company.class)
                .setParameter("id", id).uniqueResultOptional();
    }

    public List<Company> getCompanies() {
        return sessionFactory.getCurrentSession().createQuery("from Company", Company.class).list();
    }

    public void incrementView(Integer id) {
        sessionFactory.getCurrentSession()
                .createQuery("update Company set viewsCount = viewsCount + 1 WHERE id = :id")
                .setParameter("id", id).executeUpdate();
    }

    public Integer save(Company company) {
        sessionFactory.getCurrentSession().save(company);
        return company.getCompanyId();
    }

    public void updateComment(Integer id, String comment) {
        sessionFactory.getCurrentSession()
                .createQuery("update Company set comment = :comment WHERE companyId = :id")
                .setParameter("comment", comment)
                .setParameter("id", id).executeUpdate();
    }

    public void deleteCompany(Integer id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Company WHERE companyId = :id")
                .setParameter("id", id).executeUpdate();
    }

    public void updateCompany(Company company) {
        sessionFactory.getCurrentSession().merge(company);
    }
}
