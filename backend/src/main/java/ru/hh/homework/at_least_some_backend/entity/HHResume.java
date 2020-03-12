package ru.hh.homework.at_least_some_backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "hh_resume")
public class HHResume extends HHEntity
{
    @Column(name = "title")
    private String title;

    @JoinColumn(name = "user_id") @ManyToOne
    private HHUser user;

    @Column(name = "experience")
    private String experience;

    @Column(name = "contacts")
    private String contacts;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public HHUser getUser() { return user; }
    public void setUser(HHUser user) { this.user = user; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getContacts() { return contacts; }
    public void setContacts(String contacts) { this.contacts = contacts; }
}
