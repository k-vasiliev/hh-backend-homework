package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.FavoritesVacancy;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class FavoritesVacancyDao {

    private final SessionFactory sessionFactory;

    public FavoritesVacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory session() {
        return sessionFactory;
    }

    public List<FavoritesVacancy> getAll(Integer limit, Integer page) {
        return session()
                .getCurrentSession()
                .createQuery("SELECT f FROM FavoritesVacancy f ORDER BY f.vacancyId", FavoritesVacancy.class)
                .setFirstResult(page*limit)
                .setMaxResults(limit)
                .getResultList();
    }

    public Optional<FavoritesVacancy> get(Integer vacancyId) {
        return Optional.ofNullable(session().getCurrentSession().get(FavoritesVacancy.class, vacancyId));
    }

    public FavoritesVacancy update(FavoritesVacancy favoritesVacancy) {
        session().getCurrentSession().update(favoritesVacancy);
        return favoritesVacancy;
    }

    public FavoritesVacancy create(FavoritesVacancy favoritesVacancy) {
        session().getCurrentSession().persist(favoritesVacancy);
        return favoritesVacancy;
    }

    public FavoritesVacancy saveOrUpdate(FavoritesVacancy favoritesVacancy) {
        session().getCurrentSession().saveOrUpdate(favoritesVacancy);
        return favoritesVacancy;
    }

    public void delete(FavoritesVacancy favoritesVacancy) {
        session().getCurrentSession().delete(favoritesVacancy);
    }
}
