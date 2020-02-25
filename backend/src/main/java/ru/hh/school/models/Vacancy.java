package ru.hh.school.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id",  updatable = false)
    private Integer id;

    @Column (name = "company_id")
    private Integer companyId;

    @Column (name = "title")
    private String title;

    @Column (name = "compensation")
    private BigInteger compensation;

    @Column (name = "description")
    private String description;

    @Column (name = "contacts")
    private String contacts;

    @CreationTimestamp
    @Column (name = "creation_date")
    private Timestamp creationDate;

    @UpdateTimestamp
    @Column (name = "update_date")
    private Timestamp updateDate;

    // ToDo: no-arg constructor
    public Vacancy (){

    }
}
