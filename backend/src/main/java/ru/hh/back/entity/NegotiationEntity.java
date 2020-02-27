package ru.hh.back.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "negotiation")
public class NegotiationEntity {
    @OneToOne
    @JoinColumn(name = "resumeId")
    private ResumeEntity resume;

    @OneToOne
    @JoinColumn(name = "vacancyId")
    private VacancyEntity vacancy;

    public NegotiationEntity() {

    }

    public ResumeEntity getResume() {
        return resume;
    }

    public void setResume(ResumeEntity resume) {
        this.resume = resume;
    }

    public VacancyEntity getVacancy() {
        return vacancy;
    }

    public void setVacancy(VacancyEntity vacancy) {
        this.vacancy = vacancy;
    }
}
