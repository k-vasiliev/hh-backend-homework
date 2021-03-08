package ru.hh.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.CommentDao;

@Service
public class CommentService {

    private final CommentDao commentDao;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Transactional
    public void updateComment(Integer employerId, String comment) {
        try {
            commentDao.updateComment(employerId, comment);
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.info("OptimisticLockingException for Comment Class");
            commentDao.updateComment(employerId, comment);
        }
    }

}
