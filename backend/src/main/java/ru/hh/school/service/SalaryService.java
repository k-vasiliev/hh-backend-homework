package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.SalaryDao;
import ru.hh.school.entity.Salary;

import javax.inject.Singleton;

@Singleton
public class SalaryService {

    private SalaryDao salaryDao;

    public SalaryService(SalaryDao salaryDao) {
        this.salaryDao = salaryDao;
    }

    @Transactional
    public void saveOrUpdate(Salary salary) {
        salaryDao.saveOrUpdate(salary);
    }
}
