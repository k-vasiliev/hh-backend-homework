package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.FavoritesEmployer;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class FavoritesEmployerDao {

    private final SessionFactory sessionFactory;

    public FavoritesEmployerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory session() {
        return sessionFactory;
    }

    public List<FavoritesEmployer> getAll(Integer limit, Integer page) {
        return session()
                .getCurrentSession()
                .createQuery("SELECT f FROM FavoritesEmployer f ORDER BY f.employerId", FavoritesEmployer.class)
                .setFirstResult(page*limit)
                .setMaxResults(limit)
                .getResultList();
    }

    public Optional<FavoritesEmployer> get(Integer employerId) {
        return Optional.ofNullable(session().getCurrentSession().get(FavoritesEmployer.class, employerId));
    }

    public FavoritesEmployer update(FavoritesEmployer favoritesEmployer) {
        session().getCurrentSession().update(favoritesEmployer);
        return favoritesEmployer;
    }

    public FavoritesEmployer create(FavoritesEmployer favoritesEmployer) {
        session().getCurrentSession().persist(favoritesEmployer);
        return favoritesEmployer;
    }

    public FavoritesEmployer saveOrUpdate(FavoritesEmployer favoritesEmployer) {
        session().getCurrentSession().saveOrUpdate(favoritesEmployer);
        return favoritesEmployer;
    }

    public void delete(FavoritesEmployer favoritesEmployer) {
        session().getCurrentSession().delete(favoritesEmployer);
    }

}
