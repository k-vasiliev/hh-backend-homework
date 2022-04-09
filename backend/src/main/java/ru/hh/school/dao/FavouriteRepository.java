package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.school.domain.Favourite;
import ru.hh.school.domain.FavouriteType;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class FavouriteRepository extends AbstractRepository<Favourite> {
    @Autowired
    public FavouriteRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Favourite> getAllByFavouriteType(FavouriteType favouriteType) {
        Session session = getSessionFactory().getCurrentSession();
        return session.createQuery("SELECT u From Favourite u WHERE u.type = :favouriteType", Favourite.class)
                .setParameter("favouriteType", favouriteType)
                .getResultList();
    }

    public Optional<Favourite> getById(Long favouriteId) {
        Session session = getSessionFactory().getCurrentSession();
        Favourite favourite = session.createQuery("SELECT u From Favourite u WHERE u.id = :id", Favourite.class)
                .setParameter("id", favouriteId)
                .getSingleResult();
        return Optional.of(favourite);
    }

    public Favourite getFavouriteByVacancyId(Long vacancyId) {
        try {
            Session session = getSessionFactory().getCurrentSession();
            return session.createQuery("SELECT u From Favourite u WHERE u.linkId = :vacancyId AND u.type = :type", Favourite.class)
                    .setParameter("type", FavouriteType.VACANCY)
                    .setParameter("vacancyId", vacancyId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Favourite getFavouriteByEmployerId(Long employerId) {
        try {
            Session session = getSessionFactory().getCurrentSession();
            return session.createQuery("SELECT u From Favourite u WHERE u.linkId = :employerId AND u.type = :type", Favourite.class)
                    .setParameter("type", FavouriteType.EMPLOYER)
                    .setParameter("employerId", employerId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}