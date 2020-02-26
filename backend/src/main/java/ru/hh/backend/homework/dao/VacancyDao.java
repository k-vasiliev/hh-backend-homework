package ru.hh.backend.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.backend.homework.entity.VacancyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class VacancyDao {
    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public VacancyEntity save(VacancyEntity vacancyEntity) {
        getSessionFactory().getCurrentSession().save(vacancyEntity);
        return vacancyEntity;
    }

    public Optional<VacancyEntity> get(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        try {
            VacancyEntity vacancy = session
                    .createQuery("SELECT v FROM VacancyEntity v WHERE v.id = :id", VacancyEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(vacancy);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public List<VacancyEntity> getAll() {
        return getSessionFactory()
                .getCurrentSession()
                .createQuery("SELECT v FROM VacancyEntity v", VacancyEntity.class)
                .getResultList();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
