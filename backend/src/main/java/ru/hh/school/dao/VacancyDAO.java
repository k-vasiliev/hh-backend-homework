package ru.hh.school.dao;

import org.hibernate.SessionFactory;

public class VacancyDAO extends BaseDAO {
    public VacancyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
