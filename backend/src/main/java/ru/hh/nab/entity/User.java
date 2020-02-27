package ru.hh.nab.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    public User(String userName, String type, Date lastUpdate, boolean active) {
        this.userName = userName;
        this.type = type;
        this.lastUpdate = lastUpdate;
        this.active = active;
    }

    public User() {
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resume> resumes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Company> companies = new HashSet<>();

    public void addResume(Resume resume) {
        this.resumes.add(resume);
        resume.setUser(this);
    }

    public void removeResume(Resume resume) {
        this.resumes.remove(resume);
        resume.setUser(null);
    }

    public void addCompany(Company company) {
        this.companies.add(company);
        company.setUser(this);
    }

    public void removeCompany(Company company) {
        this.companies.remove(company);
        company.setUser(null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private int userId;

    @Column(name = "name")
    private String userName;

    @Column(name = "type")
    private String type;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update")
    private Date lastUpdate;

    @Column(name = "active")
    private boolean active;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public boolean isActive() {
        return active;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
