package dao;

import entity.VacancyEntity;
import entity.VacancyResponseEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VacancyResponseDao {
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory s) {
        this.sessionFactory = s;
    }

    public VacancyResponseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<VacancyResponseEntity> getVacancyResponses() {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From VacancyResponseEntity a", VacancyResponseEntity.class)
                .list();
    }
}
