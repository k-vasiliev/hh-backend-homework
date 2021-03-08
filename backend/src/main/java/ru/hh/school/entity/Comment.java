package ru.hh.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Comment {

    public Comment() {}

    private int id;

    @Column(name = "comment")
    private String comment;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
