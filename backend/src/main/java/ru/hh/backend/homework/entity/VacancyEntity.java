package ru.hh.backend.homework.entity;

import jnr.ffi.annotations.In;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "vacancies")
public class VacancyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id")
    private Integer vacancyId;

    @CreationTimestamp
    @Column(name = "creation_date")
    private Date creationDate;

    @UpdateTimestamp
    @Column(name = "modification_date")
    private Date modificationDate;

    @Column(name = "title")
    private String title;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Integer companyEntity;

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(Integer companyEntity) {
        this.companyEntity = companyEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyEntity vacancyEntity = (VacancyEntity) o;
        return vacancyId.equals(vacancyEntity.vacancyId) &&
                creationDate.equals(vacancyEntity.creationDate) &&
                modificationDate.equals(vacancyEntity.modificationDate) &&
                title.equals(vacancyEntity.title) &&
                salary.equals(vacancyEntity.salary) &&
                description.equals(vacancyEntity.description) &&
                companyEntity.equals(vacancyEntity.companyEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancyId, creationDate, modificationDate, title, salary, description, companyEntity);
    }
}
