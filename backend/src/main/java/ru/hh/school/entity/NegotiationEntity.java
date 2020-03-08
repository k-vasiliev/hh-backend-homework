package ru.hh.school.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "negotiation")
public class NegotiationEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    @Column
    private LocalDate creation_time;

    @UpdateTimestamp
    @Column
    private LocalDate last_modify;


    @OneToOne
    @JoinColumn(name = "id")
    private ResumeEntity resume;

    @OneToOne
    @JoinColumn(name = "id")
    private VacancyEntity vacancy;

    public NegotiationEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDate creation_time) {
        this.creation_time = creation_time;
    }

    public LocalDate getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(LocalDate last_modify) {
        this.last_modify = last_modify;
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
