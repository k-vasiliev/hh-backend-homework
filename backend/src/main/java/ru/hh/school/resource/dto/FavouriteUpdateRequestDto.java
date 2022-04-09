package ru.hh.school.resource.dto;

public class FavouriteUpdateRequestDto {
    private String comment; // комментарий. Может быть пустым

    public FavouriteUpdateRequestDto() {
    }

    public FavouriteUpdateRequestDto(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
