package ru.hh.school.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoritesEmployerRequest {

    @JsonProperty("employer_id")
    private Integer employerId;
    private String comment;

    public FavoritesEmployerRequest() {
    }

    public FavoritesEmployerRequest(Integer employerId, String comment) {
        this.employerId = employerId;
        this.comment = comment;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
