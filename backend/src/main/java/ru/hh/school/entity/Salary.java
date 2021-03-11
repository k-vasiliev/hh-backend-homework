package ru.hh.school.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Table(name = "salary")
public class Salary {

    @Id
    @Column(name = "vacancy_id" , nullable = false)
    private Integer vacancyId;

    @OneToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @Column(name = "from")
    private Integer from;

    @Column(name = "to")
    private Integer to;

    @Column(name = "currency")
    private String currency;

    @Column(name = "gross")
    private Boolean gross;

}
