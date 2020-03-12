package ru.hh.homework.at_least_some_backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "hh_negotiation")
public class HHNegotiation extends HHEntity
{
    @JoinColumn(name = "vacancy_id") @ManyToOne
    private HHVacancy vacancy;
    @JoinColumn(name = "resume_id") @ManyToOne
    private HHResume resume;

    public HHVacancy getVacancy() { return vacancy; }
    public void setVacancy(HHVacancy vacancy) { this.vacancy = vacancy; }

    public HHResume getResume() { return resume; }
    public void setResume(HHResume resume) { this.resume = resume; }
}
