package ru.hh.school.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavouriteVacancyRequestDto {
    @JsonProperty(value = "vacancy_id")
    private Long vacancyId;
    private String comment;

    public FavouriteVacancyRequestDto() {
    }

    public FavouriteVacancyRequestDto(Long vacancyId, String comment) {
        this.vacancyId = vacancyId;
        this.comment = comment;
    }

    public Long getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Long vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
