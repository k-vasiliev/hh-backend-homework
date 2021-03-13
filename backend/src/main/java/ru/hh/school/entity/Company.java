package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "company_name")
    private String name;

    @Column(name = "date_create")
    protected LocalDate creationDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    private Integer viewsCount;

    public Company() {
    }

    public Company(Integer id, Integer companyId, String name, String description, Area area, String comment, Integer viewsCount, LocalDate creationDate) {
        this.id = id;
        this.companyId = companyId;
        this.name = name;
        this.description = description;
        this.area = area;
        this.comment = comment;
        this.viewsCount = viewsCount;
        this.creationDate = creationDate;
    }

    public Company(Integer companyId, String name, String description, Area area, String comment, Integer viewsCount, LocalDate date) {
        this.companyId = companyId;
        this.name = name;
        this.description = description;
        this.area = area;
        this.comment = comment;
        this.viewsCount = viewsCount;
        this.creationDate = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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
