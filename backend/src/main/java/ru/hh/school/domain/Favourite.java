package ru.hh.school.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "favourite")
public class Favourite extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @OneToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    private Long linkId;

    private FavouriteType type;

    private String comment;

    private Long viewsCount;

    private LocalDateTime dateCreate;

    public Favourite() {
    }

    public Favourite(Vacancy vacancy, Long linkId, FavouriteType type, String comment, Long viewsCount) {
        this.vacancy = vacancy;
        this.linkId = linkId;
        this.type = type;
        this.comment = comment;
        this.viewsCount = viewsCount;
        this.dateCreate = LocalDateTime.now();
    }

    public Favourite(Employer employer, Long linkId, FavouriteType type, String comment, Long viewsCount) {
        this.employer = employer;
        this.linkId = linkId;
        this.type = type;
        this.comment = comment;
        this.viewsCount = viewsCount;
        this.dateCreate = LocalDateTime.now();
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
}
