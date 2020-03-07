package dao;

import dto.NewVacancyDto;
import entity.VacancyEntity;
import entity.VacancyResponseEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

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

    public VacancyEntity getVacancyById(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From VacancyEntity a Where a.id=:id", VacancyEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<VacancyResponseEntity> getVacancyResponses(Integer vacancyId) {
        return sessionFactory.getCurrentSession()
                .createQuery("Select a From VacancyResponseEntity a Where a.vacancy.id=:id", VacancyResponseEntity.class)
                .setParameter("id", vacancyId)
                .list();
    }

    public Integer addNegotiation(Integer resumeId, Integer vacancyId) {
        VacancyResponseEntity newResponse = new VacancyResponseEntity(resumeId, vacancyId);
        sessionFactory.getCurrentSession().persist(newResponse);
        return newResponse.getId();
    }

    public Integer addVacancy(NewVacancyDto vacancyDto) {
        VacancyEntity vacancy= new VacancyEntity(vacancyDto);
        sessionFactory.getCurrentSession().persist(vacancy);
        return vacancy.getId();
    }
}
