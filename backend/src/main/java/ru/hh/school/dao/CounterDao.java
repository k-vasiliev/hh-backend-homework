package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.entity.counter.EmployerCounter;
import ru.hh.school.entity.counter.VacancyCounter;

import javax.ws.rs.NotFoundException;

public class CounterDao extends GenericDao {

    public CounterDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void incrementEmployerViews(Integer counterId) {
        EmployerCounter counter = get(EmployerCounter.class, counterId).orElseThrow(NotFoundException::new);
        counter.setCounter(counter.getCounter() + 1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void incrementVacancyViews(Integer counterId) {
        VacancyCounter counter = get(VacancyCounter.class, counterId).orElseThrow(NotFoundException::new);
        counter.setCounter(counter.getCounter() + 1);
    }

}
