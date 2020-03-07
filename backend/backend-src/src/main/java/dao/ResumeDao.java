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
        var ret = sessionFactory.getCurrentSession()
                .createQuery("Select a From ResumeEntity a", ResumeEntity.class)
                .list();

        return ret;
    }

    public Integer newResume(ResumeEntity resume) {
        sessionFactory.getCurrentSession().persist(resume);
        return resume.getId();
    }

    public ResumeEntity getResumeById(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From ResumeEntity a Where a.id=:id", ResumeEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
