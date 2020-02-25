package ru.hh.school.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "company_id",  updatable = false)
    private Integer id;

    @Column (name = "user_id")
    private Integer userId;

    @Column (name = "title")
    private String title;

    @CreationTimestamp
    @Column (name = "creation_date")
    private Timestamp creationDate;

    @UpdateTimestamp
    @Column (name = "update_date")
    private Timestamp updateDate;

    // ToDo: no-arg constructor
    public Company (){

    }
}
