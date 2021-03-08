package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.entity.EmployerCounter;

import javax.ws.rs.NotFoundException;

public class ViewsCounterDao extends GenericDao {

    public ViewsCounterDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void incrementEmployerViews(Integer counterId) {
        EmployerCounter counter = get(EmployerCounter.class, counterId).orElseThrow(NotFoundException::new);
        counter.setCounter(counter.getCounter() + 1);
    }

}
