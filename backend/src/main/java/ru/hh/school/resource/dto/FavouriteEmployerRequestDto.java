package ru.hh.school.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavouriteEmployerRequestDto {
    @JsonProperty(value = "employer_id")
    private Long employerId; // id компании, данные которой нужно сохранить
    private Long comment; // комментарий. Может быть пустым

    public FavouriteEmployerRequestDto() {
    }

    public FavouriteEmployerRequestDto(Long employerId, Long comment) {
        this.employerId = employerId;
        this.comment = comment;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public Long getComment() {
        return comment;
    }

    public void setComment(Long comment) {
        this.comment = comment;
    }
}
