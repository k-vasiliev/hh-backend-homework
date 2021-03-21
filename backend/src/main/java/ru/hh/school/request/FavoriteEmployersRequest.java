package ru.hh.school.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoriteEmployersRequest {

    @JsonProperty("employer_id")
    private Integer employerId;
    private String comment;

    public FavoriteEmployersRequest(Integer employerId, String comment) {
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
