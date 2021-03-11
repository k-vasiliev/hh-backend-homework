package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.entity.Comment;
import ru.hh.school.entity.EmployerComment;

import javax.ws.rs.NotFoundException;

@Repository
public class CommentDao extends GenericDao {

    public CommentDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateComment(Integer commentId, String comment) {
        Comment commentEntity = get(EmployerComment.class, commentId).orElseThrow(NotFoundException::new);
        commentEntity.setComment(comment);
    }

}
