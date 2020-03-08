package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.VacancyEntity;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Singleton
public class VacancyDao {
    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public VacancyEntity create(VacancyEntity vacancyEntity){
        sessionFactory.getCurrentSession().save(vacancyEntity);
        return vacancyEntity;
    }

    public Optional<VacancyEntity> get(Long id) {
        return Optional.of(sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM Vacancy u WHERE u.id = :id", VacancyEntity.class)
                .setParameter("id", id).getSingleResult());

    }

    public List<VacancyEntity> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Vacancy", VacancyEntity.class)
                .getResultList();
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
