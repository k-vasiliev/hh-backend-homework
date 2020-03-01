package ru.hh.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resume")
public class Resume extends BaseEntity {

    @Id
    @Column(name = "resume_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resumeId;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String experience;

    @Column
    private String contacts;

    public Resume() {
    }

    public Resume(String title, User user, String experience, String contacts, LocalDate date) {
        this.title = title;
        this.user = user;
        this.experience = experience;
        this.contacts = contacts;
        super.creationDate = date;
        super.lastUpdateDate = date;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
