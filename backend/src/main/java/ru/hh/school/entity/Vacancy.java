package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @Column(name = "vacancy_id")
    private Integer id;

    @Column
    private String name;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @Column(name = "compensation_from")
    private Integer compensationFrom;

    @Column(name = "compensation_to")
    private Integer compensationTo;

    @Column
    private String currency;

    @Column
    private boolean gross;

    @Column(name = "created_at")
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @Column(name = "views_count")
    private Integer viewsCount;

    @Column
    private String comment;

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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Integer getCompensationFrom() {
        return compensationFrom;
    }

    public void setCompensationFrom(Integer compensationFrom) {
        this.compensationFrom = compensationFrom;
    }

    public Integer getCompensationTo() {
        return compensationTo;
    }

    public void setCompensationTo(Integer compensationTo) {
        this.compensationTo = compensationTo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isGross() {
        return gross;
    }

    public void setGross(boolean gross) {
        this.gross = gross;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id.equals(vacancy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
