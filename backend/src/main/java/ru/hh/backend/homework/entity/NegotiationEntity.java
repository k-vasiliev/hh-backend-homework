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
    private Integer id;

    @CreationTimestamp
    @Column(name = "creation_date")
    private Date creationDate;

    @UpdateTimestamp
    @Column(name = "modification_date")
    private Date modificationDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private ResumeEntity resumeEntity;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private VacancyEntity vacancyEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return id.equals(that.id) &&
                creationDate.equals(that.creationDate) &&
                modificationDate.equals(that.modificationDate) &&
                resumeEntity.equals(that.resumeEntity) &&
                vacancyEntity.equals(that.vacancyEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, modificationDate, resumeEntity, vacancyEntity);
    }
}
