package ru.hh.school.dao;

import org.hibernate.SessionFactory;

public class EmployerDAO extends BaseDAO {
    public EmployerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
