package ru.hh.school.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favourite")
public class Favourite extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    private Long linkId;

    private FavouriteType type;

    private String comment;

    private Long viewsCount;

    private LocalDateTime dateCreate;

    public Favourite() {
    }

    public Favourite(Vacancy vacancy, Long linkId, FavouriteType type, String comment) {
        this.vacancy = vacancy;
        this.linkId = linkId;
        this.type = type;
        this.comment = comment;
        this.dateCreate = LocalDateTime.now();
        this.viewsCount = 0L;
    }

    public Favourite(Employer employer, Long linkId, FavouriteType type, String comment) {
        this.employer = employer;
        this.linkId = linkId;
        this.type = type;
        this.comment = comment;
        this.dateCreate = LocalDateTime.now();
        this.viewsCount = 0L;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public FavouriteType getType() {
        return type;
    }

    public void setType(FavouriteType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "employer=" + employer +
                ", vacancy=" + vacancy +
                ", linkId=" + linkId +
                ", type=" + type +
                ", comment='" + comment + '\'' +
                ", viewsCount=" + viewsCount +
                ", dateCreate=" + dateCreate +
                '}';
    }
}
