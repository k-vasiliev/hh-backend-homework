package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vacancy")
public class VacancyEntity {

    @Id
    private Integer id;

    private String name;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "area_id")
    private AreaEntity area;

    private Double salary;

    @Column(name = "created_at")
    private String createdAt;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employer_id")
    private VacancyEmployerEntity employer;

    @Transient
    private String popularity;

    @Column(name = "views_count")
    private Integer viewsCount;

    private String comment;

    public VacancyEntity() {
    }

    public VacancyEntity(Integer id, String name, LocalDateTime dateCreate, AreaEntity area,
                         Double salary, String createdAt, VacancyEmployerEntity employer, String popularity,
                     Integer viewsCount, String comment) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
        this.area = area;
        this.salary = salary;
        this.createdAt = createdAt;
        this.employer = employer;
        this.popularity = popularity;
        this.viewsCount = viewsCount;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public AreaEntity getArea() {
        return area;
    }

    public void setArea(AreaEntity area) {
        this.area = area;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public VacancyEmployerEntity getEmployer() {
        return employer;
    }

    public void setEmployer(VacancyEmployerEntity employer) {
        this.employer = employer;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
