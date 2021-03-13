package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vacancy_id")
    private Integer vacancyId;

    @Column(name = "vacancy_name")
    private String name;

    @Column(name = "date_create")
    protected LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "salary_id")
    private Salary salary;

    @Column(name = "created_at")
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Company employer;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    private Integer viewsCount;

    public Vacancy() {
    }

    public Vacancy(Integer id, Integer vacancyId, String name, LocalDate creationDate, Area area, Salary salary, String createdAt, Company employer, String comment, Integer viewsCount) {
        this.id = id;
        this.vacancyId = vacancyId;
        this.name = name;
        this.creationDate = creationDate;
        this.area = area;
        this.salary = salary;
        this.createdAt = createdAt;
        this.employer = employer;
        this.comment = comment;
        this.viewsCount = viewsCount;
    }

    public Vacancy(Integer vacancyId, String name, Area area, Salary salary, String createdAt, Company employer, String comment, Integer viewsCount, LocalDate creationDate) {
        this.vacancyId = vacancyId;
        this.name = name;
        this.creationDate = creationDate;
        this.area = area;
        this.salary = salary;
        this.createdAt = createdAt;
        this.employer = employer;
        this.comment = comment;
        this.viewsCount = viewsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.employer = employer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }
}
