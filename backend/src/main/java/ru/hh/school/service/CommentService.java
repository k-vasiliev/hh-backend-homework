package ru.hh.school.service;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.CommentDao;
import ru.hh.school.entity.Comment;

@Service
public class CommentService {

    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Transactional
    public void updateComment(Comment commentEntity, String comment) {
        try {
            commentDao.updateComment(commentEntity, comment);
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("OPTIM EXCEPT" + e);
            commentDao.refresh(commentEntity);
            commentDao.updateComment(commentEntity, comment);
        }
    }

}
