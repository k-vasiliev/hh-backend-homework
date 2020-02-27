package ru.hh.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "resume")
public class Resume extends Model {
    public Resume(String title, String workExperience, String contacts, User user) {
        this.title = title;
        this.workExperience = workExperience;
        this.contacts = contacts;
        this.user = user;
    }

    @Column(name = "resume_tilte")
    private String title;

    @Column(name = "work_experience")
    private String workExperience;

    @Column
    private String contacts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
