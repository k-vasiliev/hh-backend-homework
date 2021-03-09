package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.ViewsCounterDao;

@Service
public class CounterService {

    private final ViewsCounterDao counterDao;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CounterService(ViewsCounterDao counterDao) {
        this.counterDao = counterDao;
    }

    @Transactional
    public void incrementEmployerCounter(Integer counterId) {
        try {
            counterDao.incrementEmployerViews(counterId);
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.info("OptimisticLockingException for Counter Class");
            counterDao.incrementEmployerViews(counterId);
        }
    }

    @Transactional
    public void incrementVacancyCounter(Integer counterId) {
        try {
            counterDao.incrementVacancyViews(counterId);
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.info("OptimisticLockingException for Counter Class");
            counterDao.incrementVacancyViews(counterId);
        }
    }

}
