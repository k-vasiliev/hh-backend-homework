package ru.hh.school.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class FavoritesVacancyRequestDto {

    @NotNull
    @JsonProperty(value = "vacancy_id")
    private Integer vacancyId;

    @JsonProperty(required = true)
    private String comment;

    public FavoritesVacancyRequestDto(@NotNull Integer vacancyId, String comment) {
        this.vacancyId = vacancyId;
        this.comment = comment;
    }

    public FavoritesVacancyRequestDto() {
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public String getComment() {
        return comment;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
