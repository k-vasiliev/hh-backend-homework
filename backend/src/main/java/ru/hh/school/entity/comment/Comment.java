package ru.hh.school.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@MappedSuperclass
public abstract class Comment {

    public Comment() {}

    public Comment(String comment) {
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "comment")
    private String comment;

    @Version
    @JsonIgnore
    private Integer version;

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Comment[" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", version=" + version +
                ']';
    }

}
