package ru.hh.school.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@Table(name = "favorites_vacancy")
public class FavoritesVacancy extends Popularity {

    @Id
    @Column(name = "vacancy_id" , nullable = false)
    private Integer vacancyId;

    @OneToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    private Integer viewsCount;

    public FavoritesVacancy(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public FavoritesVacancy(Integer vacancyId, String comment, LocalDateTime created) {
        this.vacancyId = vacancyId;
        this.comment = comment;
        this.created = created;
    }

    public FavoritesVacancy() {
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    @Override
    public String getPopularityValue(Integer viewsCount) {
        return super.getPopularityValue(viewsCount);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
