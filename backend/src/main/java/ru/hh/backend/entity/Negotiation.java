package ru.hh.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "negotiation")
public class Negotiation extends BaseEntity {
    @Id
    @Column(name = "negotiation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer responseId;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    public Negotiation() {
    }

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
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
