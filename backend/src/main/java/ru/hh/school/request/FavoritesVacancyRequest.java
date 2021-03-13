package ru.hh.school.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoritesVacancyRequest {

    @JsonProperty("vacancy_id")
    private Integer vacancyId;

    private String comment;

    public FavoritesVacancyRequest() {
    }

    public FavoritesVacancyRequest(Integer vacancyId, String comment) {
        this.vacancyId = vacancyId;
        this.comment = comment;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
