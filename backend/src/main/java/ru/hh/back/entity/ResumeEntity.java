package ru.hh.back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resume")
public class ResumeEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
    @Column(name = "workExperience", nullable = false)
    private String workExperience;
    @Column(name = "contacts", nullable = false)
    private String contacts;
    public ResumeEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
}
