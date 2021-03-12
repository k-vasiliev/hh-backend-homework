package ru.hh.school.service;

import org.hibernate.StaleStateException;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.ViewsCounterDao;

import javax.ws.rs.NotFoundException;

@Service
public class CounterService {

    private final ViewsCounterDao counterDao;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CounterService(ViewsCounterDao counterDao) {
        this.counterDao = counterDao;
    }

    @Transactional
    public void incrementEmployerCounter(Integer counterId) {
        int retryCounter = 0;
        while (true) {
            if (executeIncrement(() -> counterDao.incrementEmployerViews(counterId))) { break; }
            retryCounter += 1;
        }
        logger.info("Retry counter: " + retryCounter);
    }

    @Transactional
    public void incrementVacancyCounter(Integer counterId) {
        int retryCounter = 0;
        while (true) {
            if (vacancyCounterIncrementIsCompleted(counterId)) { break; }
            retryCounter += 1;
        }
        logger.info("Retry counter: " + retryCounter);
    }

    private boolean employerCounterIncrementIsCompleted(Integer counterId) {
        try {
            counterDao.incrementEmployerViews(counterId);
            logger.info("Increment success");
            return true;
        } catch (ObjectOptimisticLockingFailureException | StaleStateException  e) {
            logger.info("OptimisticLockingException for Counter Class");
            return false;
        } catch (NotFoundException e) {
            logger.info("Counter has been deleted");
            return true;
        }
    }

    private boolean vacancyCounterIncrementIsCompleted(Integer counterId) {
        try {
            counterDao.incrementVacancyViews(counterId);
            logger.info("Increment success");
            return true;
        } catch (ObjectOptimisticLockingFailureException | StaleStateException  e) {
            logger.info("OptimisticLockingException for Counter Class");
            return false;
        } catch (NotFoundException e) {
            logger.info("Counter has been deleted");
            return true;
        }
    }

    private boolean executeIncrement(Runnable incrementMethod) {
        try {
            incrementMethod.run();
            logger.info("Increment success");
            return true;
        } catch (ObjectOptimisticLockingFailureException | StaleStateException  e) {
            logger.info("OptimisticLockingException for Counter Class");
            return false;
        } catch (NotFoundException e) {
            logger.info("Counter has been deleted");
            return true;
        }
    }

}
