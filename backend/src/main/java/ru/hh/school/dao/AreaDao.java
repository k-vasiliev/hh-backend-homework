package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AreaDao extends GenericDao {
    public AreaDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
