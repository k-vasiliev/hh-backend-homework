package dao;

import entity.ResumeEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResumeDao {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory s) {
        this.sessionFactory = s;
    }

    public ResumeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<ResumeEntity> getResumes() {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From ResumeEntity a", ResumeEntity.class)
                .list();
    }

    public void newResume(ResumeEntity resume) {
        sessionFactory.getCurrentSession().persist(resume);
    }
}
