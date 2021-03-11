package ru.hh.school.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.EmployerApiHh;
import ru.hh.school.entity.Employer;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.mapper.EmployerMapper;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;

@Singleton
public class EmployerService {

    private final EmployerDao employerDao;

    private final EmployerMapper employerMapper;

    private final ApiHhService apiHhService;

    public EmployerService(EmployerDao employerDao, EmployerMapper employerMapper, ApiHhService apiHhService) {
        this.employerDao = employerDao;
        this.employerMapper = employerMapper;
        this.apiHhService = apiHhService;
    }

    @Transactional
    public Employer get(Integer employerId) {
        return employerDao.get(employerId).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Employer create(Employer employer) {
        return employerDao.create(employer);
    }

    @Transactional
    public Employer saveOrUpdate(Employer employer) {
        return employerDao.saveOrUpdate(employer);
    }

    @Transactional
    public void delete(Employer employer) {
        employerDao.delete(employer);
    }

    @Transactional
    public void update(Integer id) throws HhRequestException {
        if (id  == null) {
            throw new HhRequestException("employer id is empty");
        }

        EmployerApiHh employer = apiHhService.getEmployerBy(id);
        this.saveOrUpdate(employerMapper.map(employer));
    }
}
