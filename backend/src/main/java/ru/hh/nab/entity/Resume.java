package ru.hh.nab.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "resume")
public class Resume {

    public Resume(User users, String experience, String heading, String contacts,
                  boolean active, LocalDate lastUpdate) {
        this.users = users;
        this.experience = experience;
        this.heading = heading;
        this.contacts = contacts;
        this.active = active;
        this.lastUpdate = lastUpdate;
    }

    public Resume() {
    }

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Negotiation> negotiations = new HashSet<>();

    public void addNegotiation(Negotiation negotiation) {
        this.negotiations.add(negotiation);
        negotiation.setResume(this);
    }

    public void removeNegotiation(Negotiation negotiation) {
        this.negotiations.remove(negotiation);
        negotiation.setResume(null);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private int resId;

    @Column(name = "experience")
    private String experience;

    @Column(name = "head")
    private String heading;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "active")
    private boolean active;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "last_update")
    private LocalDate lastUpdate;

    public int getResId() {
        return resId;
    }

    public User getUser() {
        return users;
    }

    public String getExperience() {
        return experience;
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

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setUser(User user) {
        this.users = user;
    }

    public void setExperience(String experience) {
        this.experience = experience;
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

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return resId == resume.resId &&
                active == resume.active &&
                Objects.equals(experience, resume.experience) &&
                Objects.equals(heading, resume.heading) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(lastUpdate, resume.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, experience, heading, contacts, active, lastUpdate);
    }
}
