package ru.hh.backend.homework.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "company_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @CreationTimestamp
    @Column(name = "creation_date")
    protected Date creationDate;

    @UpdateTimestamp
    @Column(name = "correction_date")
    protected Date correctionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCorrectionDate() {
        return correctionDate;
    }

    public void setCorrectionDate(Date correctionDate) {
        this.correctionDate = correctionDate;
    }
}
