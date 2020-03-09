package ru.hh.school.entity;

import javax.persistence.*;

@Entity
@Table(name = "resume")
public class Resume extends CommonDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "resume_id",  updatable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "users_id")
    private User user;

    @Column (name = "title", nullable = false)
    private String title;

    @Column (name = "work_experience")
    private String workExperience;

    @Column (name = "contacts", nullable = false)
    private String contacts;

    public Resume (){
    }

    public Resume(User user, String title, String workExperience, String contacts) {
        this.user = user;
        this.title = title;
        this.workExperience = workExperience;
        this.contacts = contacts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public String getContacts() {
        return contacts;
    }
}
