package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employer")
public class EmployerEntity {

    @Id
    private Integer id;

    private String name;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    private String description;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "area_id")
    private AreaEntity area;

    private String comment;

    @Column(name = "views_count")
    private Integer viewsCount;

    @Transient
    private String popularity;

    public EmployerEntity() {
    }

    public EmployerEntity(Integer id, String name, LocalDateTime dateCreate, String description,
                          AreaEntity area, String comment, Integer viewsCount, String popularity) {
        this.id = id;
        this.name = name;
        this.dateCreate = dateCreate;
        this.description = description;
        this.area = area;
        this.comment = comment;
        this.viewsCount = viewsCount;
        this.popularity = popularity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AreaEntity getArea() {
        return area;
    }

    public void setArea(AreaEntity area) {
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

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

}
