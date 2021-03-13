package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Salary;

import javax.inject.Singleton;

@Singleton
public class SalaryDao {

    private final SessionFactory sessionFactory;

    public SalaryDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdate(Salary salary) {
        sessionFactory.getCurrentSession().saveOrUpdate(salary);
    }
}
