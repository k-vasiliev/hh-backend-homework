package dao;

import entity.UsersEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CompanyDao {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<UsersEntity> getCompanies() {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From UsersEntity a where a.isCompany = true", UsersEntity.class)
                .list();
    }
}
