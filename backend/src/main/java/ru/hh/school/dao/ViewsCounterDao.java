package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.entity.EmployerCounter;

public class ViewsCounterDao extends GenericDao {

    public ViewsCounterDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public EmployerCounter persistNewCounter() {
        EmployerCounter counter = new EmployerCounter();
        counter.setCounter(0);
        save(counter);
        return counter;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void incrementViews(EmployerCounter counter) {
        System.out.println(counter);
        counter.setCounter(counter.getCounter() + 1);
        System.out.println(counter);
    }


}
