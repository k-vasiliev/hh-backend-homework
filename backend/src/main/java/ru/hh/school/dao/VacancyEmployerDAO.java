package ru.hh.school.dao;

import org.hibernate.SessionFactory;

public class VacancyEmployerDAO extends BaseDAO {
    public VacancyEmployerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
