package ru.hh.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Table(name = "vacancy_comment")
public class VacancyComment extends Comment {

    public VacancyComment() {}

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @JsonIgnore
    private Vacancy vacancy;

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

}
