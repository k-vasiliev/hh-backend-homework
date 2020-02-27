package ru.hh.backend.homework.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
    private Date creationDate;

    @UpdateTimestamp
    @Column(name = "modification_date")
    private Date modificationDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Integer resumeEntity;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Integer vacancyEntity;

    public Integer getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Integer negotiationId) {
        this.negotiationId = negotiationId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Integer getResumeEntity() {
        return resumeEntity;
    }

    public void setResumeEntity(Integer resumeEntity) {
        this.resumeEntity = resumeEntity;
    }

    public Integer getVacancyEntity() {
        return vacancyEntity;
    }

    public void setVacancyEntity(Integer vacancyEntity) {
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
