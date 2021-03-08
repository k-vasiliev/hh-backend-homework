package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.ViewsCounterDao;
import ru.hh.school.entity.EmployerCounter;

@Service
public class CounterService {

    private final ViewsCounterDao counterDao;

    public CounterService(ViewsCounterDao counterDao) {
        this.counterDao = counterDao;
    }

    @Transactional
    public void incrementCounter(EmployerCounter counter) {
        try {
            counterDao.refresh(counter);
            counterDao.incrementViews(counter);
            System.out.println("increment counter");
            System.out.println(counter);
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("OPTIM EXCEPT" + e);
            counterDao.refresh(counter);
            counterDao.incrementViews(counter);
        }
    }

}
