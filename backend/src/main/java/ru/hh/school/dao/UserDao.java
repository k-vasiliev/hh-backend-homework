package ru.hh.school.dao;

import org.springframework.stereotype.Repository;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDao extends AbstractDao {

    public List<User> getUsers(UserType userType) {
        CriteriaQuery<User> criteriaQuery = builder().createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder().equal(root.get("type"), userType));
        return session().createQuery(criteriaQuery).getResultList();
    }

    public User getUser(Integer userId) {
        return session().get(User.class, userId);
    }
}
