package dao;

import entity.ResumeEntity;
import entity.VacancyEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class VacancyDao {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory s) {
        this.sessionFactory = s;
    }

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<VacancyEntity> getVacancies() {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From VacancyEntity a", VacancyEntity.class)
                .list();
    }
}
