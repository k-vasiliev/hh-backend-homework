package ru.hh.nab.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "negotiation")
public class Negotiation {

    public Negotiation(Resume resume, Vacancy vacancy, LocalDate lastUpdate, boolean active) {
        this.resume = resume;
        this.vacancy = vacancy;
        this.lastUpdate = lastUpdate;
        this.active = active;
    }

    public Negotiation() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "negotiation_id")
    private int negotiationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "last_update")
    LocalDate lastUpdate;

    @Column(name = "active")
    boolean active;

    public int getNegotiationId() {
        return negotiationId;
    }

    public Resume getResume() {
        return resume;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public boolean isActive() {
        return active;
    }

    public void setNegotiationId(int negotiationId) {
        this.negotiationId = negotiationId;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
