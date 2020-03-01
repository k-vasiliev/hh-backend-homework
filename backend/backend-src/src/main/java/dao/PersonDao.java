package dao;

import entity.UsersEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PersonDao {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory s) {
        this.sessionFactory = s;
    }

    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<UsersEntity> getCompanies() {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From UsersEntity a where a.isCompany = false", UsersEntity.class)
                .list();
    }
}
