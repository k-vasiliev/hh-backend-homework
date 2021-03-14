package ru.hh.school.service;

import org.hibernate.StaleStateException;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.CounterDao;
import ru.hh.school.entity.counter.EmployerCounter;
import ru.hh.school.entity.counter.VacancyCounter;

import javax.ws.rs.NotFoundException;

@Service
public class CounterService {

    private final CounterDao counterDao;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CounterService(CounterDao counterDao) {
        this.counterDao = counterDao;
    }

    public EmployerCounter createEmployerCounter() {
        return new EmployerCounter();
    }

    public VacancyCounter createVacancyCounter() {
        return new VacancyCounter();
    }

    @Transactional
    public void incrementEmployerCounter(Integer counterId) {
        executeIncrementWithRetryCounter(() -> counterDao.incrementEmployerViews(counterId));
    }

    @Transactional
    public void incrementVacancyCounter(Integer counterId) {
        executeIncrementWithRetryCounter(() -> counterDao.incrementVacancyViews(counterId));
    }

    private void executeIncrementWithRetryCounter(Runnable incrementMethod) {
        int retryCounter = 0;
        while (true) {
            if (executeIncrement(incrementMethod)) { break; }
            retryCounter += 1;
        }
        logger.info("OptimisticLocking retry counter: " + retryCounter);
    }

    private boolean executeIncrement(Runnable incrementMethod) {
        try {
            incrementMethod.run();
            return true;
        } catch (ObjectOptimisticLockingFailureException | StaleStateException  e) {
            return false;
        } catch (NotFoundException e) {
            logger.info("Counter has been deleted");
            return true;
        }
    }

}
