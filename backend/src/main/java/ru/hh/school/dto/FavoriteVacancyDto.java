package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.hh.school.entity.Popularity;
import ru.hh.school.serialize.LocalDateDeserializer;
import ru.hh.school.serialize.LocalDateSerializer;

import java.time.LocalDate;

public class FavoriteVacancyDto extends VacancyDto {

    @JsonProperty("date_create")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateCreate;

    private Popularity popularity = Popularity.REGULAR;

    @JsonProperty("views_count")
    private int viewsCount = 0;

    @JsonProperty("comment")
    private String comment;

    public FavoriteVacancyDto() {}

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "FavoriteVacancyDto[" +
                "dateCreate=" + dateCreate +
                ", popularity=" + popularity +
                ", viewsCount=" + viewsCount +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", salary=" + salary +
                ", createdAt=" + createdAt +
                ", comment=" + comment +
                ']';
    }

}
