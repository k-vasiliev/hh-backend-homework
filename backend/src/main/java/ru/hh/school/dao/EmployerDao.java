package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.EmployerEntity;

public class EmployerDao extends GenericDao {

    public EmployerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public EmployerEntity getEmployerById(int employerId) {
        return getSession()
                .createQuery("select employer " +
                        "from Employer employer " +
                        "join fetch employer.area " +
                        "where employer.id = :employerId", EmployerEntity.class)
                .setParameter("employerId", employerId)
                .getSingleResult();
    }
}
