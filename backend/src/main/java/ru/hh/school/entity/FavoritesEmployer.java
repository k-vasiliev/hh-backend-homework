package ru.hh.school.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@Table(name = "favorites_employer")
public class FavoritesEmployer extends Popularity {

    public FavoritesEmployer(Integer employerId, String comment) {
        this.employerId = employerId;
        this.comment = comment;
    }

    public FavoritesEmployer() {}

    @Id
    @Column(name = "employer_id" , nullable = false)
    private Integer employerId;

    @OneToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    private Integer viewsCount;

    public FavoritesEmployer(Integer employerId) {
        this.employerId = employerId;
    }

    public FavoritesEmployer(Integer employerId, Employer employer) {
        this.employerId = employerId;
        this.employer = employer;
    }

    public FavoritesEmployer(Integer employerId, String comment,  LocalDateTime updated) {
        this.employerId = employerId;
        this.comment = comment;
        this.updated = updated;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public Employer getEmployer() {
        return employer;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public String getComment() {
        return comment;
    }

    public void setEmployerId(Employer employer) {
        this.employer = employer;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public String getPopularityValue(Integer viewsCount) {
        return super.getPopularityValue(viewsCount);
    }
}
