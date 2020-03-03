package ru.hh.nab.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NegotiationDAO {

    private final SessionFactory sessionFactory;

    private final UserDAO userDAO;

    private final VacancyDAO vacancyDAO;

    public NegotiationDAO(SessionFactory sessionFactory, UserDAO userDAO, VacancyDAO vacancyDAO) {
        this.sessionFactory = sessionFactory;
        this.userDAO = userDAO;
        this.vacancyDAO = vacancyDAO;
    }
}
