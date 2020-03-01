package ru.hh.backend.homework.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity employer;

    public CompanyEntity() {
    }

    public CompanyEntity(String name, UserEntity employer) {
        this.name = name;
        this.employer = employer;
    }

    public CompanyEntity(Integer companyId, LocalDate creationDate, LocalDate modificationDate, String name, UserEntity employer) {
        this.companyId = companyId;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.name = name;
        this.employer = employer;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity companyEntity = (CompanyEntity) o;
        return companyId.equals(companyEntity.companyId) &&
                creationDate.equals(companyEntity.creationDate) &&
                modificationDate.equals(companyEntity.modificationDate) &&
                name.equals(companyEntity.name) &&
                employer.equals(companyEntity.employer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, creationDate, modificationDate, name, employer);
    }
}
