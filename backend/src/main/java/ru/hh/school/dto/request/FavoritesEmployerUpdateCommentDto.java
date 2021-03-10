package ru.hh.school.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoritesEmployerUpdateCommentDto {

    @JsonProperty(required = true)
    private String comment;

    public FavoritesEmployerUpdateCommentDto(String comment) {
        this.comment = comment;
    }

    public FavoritesEmployerUpdateCommentDto() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
