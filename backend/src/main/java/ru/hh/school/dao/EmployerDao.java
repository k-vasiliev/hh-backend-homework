package ru.hh.school.dao;

import org.hibernate.SessionFactory;

public class EmployerDao extends GenericDao {

    public EmployerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
