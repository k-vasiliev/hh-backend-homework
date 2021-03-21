package ru.hh.school.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoriteVacanciesRequest {

    @JsonProperty("vacancy_id")
    private Integer vacancyId;

    private String comment;

    public FavoriteVacanciesRequest(Integer vacancyId, String comment) {
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
