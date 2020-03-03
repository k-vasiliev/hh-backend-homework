package dao;

import entity.CompanyEntity;
import entity.UsersEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDao {
    SessionFactory sessionFactory;

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createCompany(CompanyEntity newCompany) {
        sessionFactory.getCurrentSession().persist(newCompany);
    }

    public List<CompanyEntity> getCompanies() {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From CompanyEntity a", CompanyEntity.class)
                .list();
    }

}
