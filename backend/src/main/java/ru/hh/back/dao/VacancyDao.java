package ru.hh.back.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.back.entity.VacancyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class VacancyDao {
    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<VacancyEntity> getVacancy() {
        List<VacancyEntity> vacancyEntities = getSessionFactory().getCurrentSession()
                .createQuery("From VacancyEntity", VacancyEntity.class)
                .list();
        return vacancyEntities;
    }

    public Optional<VacancyEntity> getVacancy(Integer id) {
        return Optional.ofNullable(getSessionFactory().getCurrentSession().get(VacancyEntity.class, id));
    }

    public Integer save(VacancyEntity vacancy) {
        getSessionFactory().getCurrentSession().saveOrUpdate(vacancy);
        return vacancy.getId();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
