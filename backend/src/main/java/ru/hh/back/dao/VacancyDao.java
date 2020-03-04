package ru.hh.back.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.back.entity.VacancyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class VacancyDao {
    private final SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<VacancyEntity> getVacancy() {
        List<VacancyEntity> vacancyEntities = getSessionFactory().getCurrentSession()
                .createQuery("From VacancyEntity", VacancyEntity.class)
                .list();
        return vacancyEntities;
    }

    @Transactional
    public Optional<VacancyEntity> getVacancy(Integer id) {
        return Optional.ofNullable(getSessionFactory().getCurrentSession().get(VacancyEntity.class, id));
    }

    @Transactional
    public Integer save(VacancyEntity vacancy) {
        getSessionFactory().getCurrentSession().saveOrUpdate(vacancy);
        return vacancy.getId();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
