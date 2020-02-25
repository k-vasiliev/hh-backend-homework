package ru.hh.school.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "resume_id",  updatable = false)
    private Integer id;

    @Column (name = "user_id")
    private Integer userId;

    @Column (name = "title")
    private String title;

    @Column (name = "work_experience")
    private String workExperience;

    @Column (name = "contacts")
    private String contacts;

    @CreationTimestamp
    @Column (name = "creation_date")
    private Timestamp creationDate;

    @UpdateTimestamp
    @Column (name = "update_date")
    private Timestamp updateDate;

    // ToDo: no-arg constructor
    public Resume (){

    }

}
