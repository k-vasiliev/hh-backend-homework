package ru.hh.backend.homework.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "negotiations")
public class NegotiationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "negotiation_id")
    private Integer negotiationId;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private ResumeEntity resumeEntity;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private VacancyEntity vacancyEntity;

    public Integer getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Integer negotiationId) {
        this.negotiationId = negotiationId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public ResumeEntity getResumeEntity() {
        return resumeEntity;
    }

    public void setResumeEntity(ResumeEntity resumeEntity) {
        this.resumeEntity = resumeEntity;
    }

    public VacancyEntity getVacancyEntity() {
        return vacancyEntity;
    }

    public void setVacancyEntity(VacancyEntity vacancyEntity) {
        this.vacancyEntity = vacancyEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NegotiationEntity that = (NegotiationEntity) o;
        return negotiationId.equals(that.negotiationId) &&
                creationDate.equals(that.creationDate) &&
                modificationDate.equals(that.modificationDate) &&
                resumeEntity.equals(that.resumeEntity) &&
                vacancyEntity.equals(that.vacancyEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(negotiationId, creationDate, modificationDate, resumeEntity, vacancyEntity);
    }
}
