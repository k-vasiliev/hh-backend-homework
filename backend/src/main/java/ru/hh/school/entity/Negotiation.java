package ru.hh.school.entity;

import javax.persistence.*;

@Entity
@Table(name = "negotiation")
public class Negotiation extends CommonDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "negotiation_id",  updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    public Negotiation() {
    }

    public Negotiation(Resume resume, Vacancy vacancy) {
        this.resume = resume;
        this.vacancy = vacancy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Resume getResume() {
        return resume;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }
}
