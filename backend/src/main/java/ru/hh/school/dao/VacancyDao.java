package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class VacancyDao {

    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Integer save(Vacancy vacancy) {
        sessionFactory.getCurrentSession().save(vacancy);
        return vacancy.getVacancyId();
    }

    public Optional<Vacancy> getVacancyByVacancyId(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Vacancy where vacancyId =:id ", Vacancy.class)
                .setParameter("id", id).uniqueResultOptional();
    }

    public List<Vacancy> getVacancies() {
        return sessionFactory.getCurrentSession().createQuery("from Vacancy", Vacancy.class).list();
    }

    public void incrementView(Integer id) {
        sessionFactory.getCurrentSession()
                .createQuery("update Vacancy set viewsCount = viewsCount + 1 WHERE id = :id")
                .setParameter("id", id).executeUpdate();
    }

    public void deleteVacancy(Integer id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Vacancy WHERE vacancyId = :id")
                .setParameter("id", id).executeUpdate();
    }

    public void updateVacancy(Vacancy vacancy) {
        sessionFactory.getCurrentSession().merge(vacancy);
    }
}
