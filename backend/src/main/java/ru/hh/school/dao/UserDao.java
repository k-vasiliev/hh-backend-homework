package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class UserDao {

    private final SessionFactory sessionFactory;
    @Inject
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User create(User user) {
        session().save(user);
        return user;
    }

    public List<User> getAll() {
        return session().createQuery("FROM User", User.class)
                .getResultList();
    }

    public User get(Integer id) {
        return session().get(User.class, id);
    }

    public List<User> getByType(UserType userType) {
        return session().createQuery("FROM User u WHERE u.userType = :userType", User.class)
                .setParameter("userType", userType)
                .getResultList();
    }

    public void deleteBy(Integer id) {
      session().createQuery("DELETE FROM User WHERE id = :id_to_delete")
              .setParameter("id_to_delete", id).executeUpdate();
    }

    public void deleteAll() {
        session().createQuery("DELETE FROM User").executeUpdate();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
