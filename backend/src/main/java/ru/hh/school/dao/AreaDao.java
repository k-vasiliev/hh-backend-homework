package ru.hh.school.dao;

import org.hibernate.SessionFactory;

public class AreaDao extends GenericDao {

    public AreaDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
