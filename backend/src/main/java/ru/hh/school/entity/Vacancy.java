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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    public Vacancy(Integer id, LocalDateTime created, String name, Area area, Employer employer) {
        this.id = id;
        this.created = created;
        this.name = name;
        this.area = area;
        this.employer = employer;
    }

    public Vacancy() {
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public Area getArea() {
        return area;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
