package ru.hh.school.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class FavoritesEmployerRequestDto {

    @NotNull
    @JsonProperty(value = "employer_id")
    private Integer employerId;

    @JsonProperty(required = true)
    private String comment;

    public FavoritesEmployerRequestDto() {
    }

    public FavoritesEmployerRequestDto(Integer employerId, String comment) {
        this.employerId = employerId;
        this.comment = comment;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public String getComment() {
        return comment;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
