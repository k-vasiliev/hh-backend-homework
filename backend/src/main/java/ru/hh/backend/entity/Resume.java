package ru.hh.backend.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "resume")
public class Resume{
/*    public Resume(String title, String workExperience, String contacts, User user) {
        this.title = title;
        this.workExperience = workExperience;
        this.contacts = contacts;
        this.user = user;
    }*/

    public Resume() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

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

    @Column(name = "resume_title")
    private String title;

    @Column(name = "work_expirience")
    private String workExperience;

    @Column
    private String contacts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
