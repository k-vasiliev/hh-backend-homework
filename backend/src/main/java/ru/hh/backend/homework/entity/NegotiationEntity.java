package ru.hh.backend.homework.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "negotiation")
public class NegotiationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreationTimestamp
    @Column(name = "creation_date")
    protected Date creationDate;

    @UpdateTimestamp
    @Column(name = "correction_date")
    protected Date correctionDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private ResumeEntity resume;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private VacancyEntity vacancy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ResumeEntity getResume() {
        return resume;
    }

    public void setResume(ResumeEntity resume) {
        this.resume = resume;
    }

    public VacancyEntity getVacancy() {
        return vacancy;
    }

    public void setVacancy(VacancyEntity vacancy) {
        this.vacancy = vacancy;
    }
}
