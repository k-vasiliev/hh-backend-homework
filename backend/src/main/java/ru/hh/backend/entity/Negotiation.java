package ru.hh.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "negotiation")
public class Negotiation extends Model {
    public Negotiation(Resume resume, Vacancy vacancy) {
        this.resume = resume;
        this.vacancy = vacancy;
    }

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}
