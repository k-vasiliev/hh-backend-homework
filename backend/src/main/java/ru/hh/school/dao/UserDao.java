package ru.hh.school.dao;

import ru.hh.school.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void create(User user) {
        session().persist(user);
    }

    @Transactional
    public Set<User> getAll() {
        return new HashSet<>(
        session().createQuery("from User", User.class).list());
    }

    public Optional<User> getBy(int id) {
        return Optional.ofNullable(
                session().get(User.class, id)
        );
    }

    public void deleteBy(int id) {
        // jpa2.1 criteria builder

      /*  CriteriaBuilder builder = session().getCriteriaBuilder();

        var query = builder.createCriteriaDelete(User.class);
        query.where(
                builder.equal(query.from(User.class).get("id"), id)
        );

        session().createQuery(query).executeUpdate();*/
      //второй подход
      getBy(id).ifPresent(
              e->session().delete(e)
      );
      //третий подход
      session().createQuery("delete from User where id = :id_to_delete")
              .setParameter("id_to_delete",id).executeUpdate();

        // + есть ещё 2 способа это сделать
    }

    public void deleteAll() {
        session().createQuery("delete from User").executeUpdate();
    }

    @Transactional
    public void update(User user) {
        session().update(user);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
