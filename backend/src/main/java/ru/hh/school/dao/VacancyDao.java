package ru.hh.school.dao;

import org.hibernate.SessionFactory;

public class VacancyDao extends GenericDao {
    public VacancyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
