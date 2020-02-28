package ru.hh.nab.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "resume")
public class Resume {

    public Resume(Users users, String exp, String heading, String contacts, boolean active, Date last_update) {
        this.users = users;
        this.exp = exp;
        this.heading = heading;
        this.contacts = contacts;
        this.active = active;
        this.last_update = last_update;
    }

    public Resume() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private int resId;

    /*@ManyToOne
    @JoinTable(name = "user", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_id")
    private int userId;*/

    @Column(name = "exp")
    private String exp;

    @Column(name = "head")
    private String heading;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "active")
    private boolean active;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update")
    private Date last_update;

    public int getResId() {
        return resId;
    }

    public Users getUser() {
        return users;
    }

    /*public int getUserId() {
        return userId;
    }*/

    public String getExp() {
        return exp;
    }

    public String getHeading() {
        return heading;
    }

    public String getContacts() {
        return contacts;
    }

    public boolean isActive() {
        return active;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setUser(Users user) {
        this.users = user;
    }

    /*public void setUserId(int userId) {
        this.userId = userId;
    }*/

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return resId == resume.resId &&
                active == resume.active &&
                Objects.equals(exp, resume.exp) &&
                Objects.equals(heading, resume.heading) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(last_update, resume.last_update);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, exp, heading, contacts, active, last_update);
    }
}
