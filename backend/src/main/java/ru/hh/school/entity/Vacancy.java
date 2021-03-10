package ru.hh.school.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @Column(name = "id" , nullable = false)
    private Integer id;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
