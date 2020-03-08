package ru.hh.school.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @CreationTimestamp
    @Column
    private LocalDate creation_time;

    @UpdateTimestamp
    @Column
    private LocalDate last_modify;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn
    private UserEntity employer;

    public CompanyEntity() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDate creation_time) {
        this.creation_time = creation_time;
    }

    public LocalDate getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(LocalDate last_modify) {
        this.last_modify = last_modify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getEmployer() {
        return employer;
    }

    public void setEmployer(UserEntity employer) {
        this.employer = employer;
    }


}


