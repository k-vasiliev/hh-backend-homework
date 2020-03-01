package ru.hh.backend.homework.entity;

import jnr.ffi.annotations.In;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "resumes")
public class ResumeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Integer resumeId;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @Column(name = "title")
    private String title;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "contacts")
    private String contacts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity applicant;

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public UserEntity getApplicant() {
        return applicant;
    }

    public void setApplicant(UserEntity applicant) {
        this.applicant = applicant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResumeEntity resumeEntity = (ResumeEntity) o;
        return resumeId.equals(resumeEntity.resumeId) &&
                creationDate.equals(resumeEntity.creationDate) &&
                modificationDate.equals(resumeEntity.modificationDate) &&
                title.equals(resumeEntity.title) &&
                workExperience.equals(resumeEntity.workExperience) &&
                contacts.equals(resumeEntity.contacts) &&
                applicant.equals(resumeEntity.applicant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeId, creationDate, modificationDate, title, workExperience, contacts, applicant);
    }
}
