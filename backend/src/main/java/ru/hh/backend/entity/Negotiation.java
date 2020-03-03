package ru.hh.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "negotiation")
public class Negotiation extends BaseEntity {
    @Id
    @Column(name = "negotiation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer negotiationId;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    public Negotiation() {
    }

    public Negotiation(Vacancy vacancy, Resume resume, LocalDate date) {
        this.vacancy = vacancy;
        this.resume = resume;
        super.creationDate = date;
        super.lastUpdateDate = date;
    }

    public Integer getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(Integer negotiationId) {
        this.negotiationId = negotiationId;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
