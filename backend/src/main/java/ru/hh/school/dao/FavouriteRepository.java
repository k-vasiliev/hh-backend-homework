package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.school.domain.Favourite;

import java.util.Optional;

@Repository
public class FavouriteRepository extends AbstractRepository<Favourite> {
    @Autowired
    public FavouriteRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Favourite> getById(Long favouriteId) {
        Session session = getSessionFactory().getCurrentSession();
        Favourite favourite = session.createQuery("SELECT u From Favourite u WHERE u.id = :id", Favourite.class)
                .setParameter("id", favouriteId)
                .getSingleResult();
        return Optional.of(favourite);
    }
}