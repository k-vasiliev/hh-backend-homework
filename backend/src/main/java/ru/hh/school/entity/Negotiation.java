package ru.hh.school.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "negotiation")
public class Negotiation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "negotiation_id",  updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @CreationTimestamp
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @UpdateTimestamp
    @Column (name = "update_date")
    private Timestamp updateDate;

    public Negotiation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
