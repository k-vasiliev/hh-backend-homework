package ru.hh.school.dao;

import org.hibernate.SessionFactory;

public class VacancyEmployerDao extends GenericDao {

    public VacancyEmployerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
