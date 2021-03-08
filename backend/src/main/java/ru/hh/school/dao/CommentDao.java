package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.entity.Comment;

@Repository
public class CommentDao extends GenericDao {
    public CommentDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Comment persistNewComment(String comment) {
        Comment newComment = new Comment(comment);
        save(newComment);
        return newComment;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateComment(Comment commentEntity, String comment) { commentEntity.setComment(comment); }

}
