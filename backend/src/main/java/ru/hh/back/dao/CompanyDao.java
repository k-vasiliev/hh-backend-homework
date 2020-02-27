package ru.hh.back.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao {
    private final SessionFactory sessionFactory;
    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
