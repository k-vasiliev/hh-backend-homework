package ru.hh.school.resource.dto;

public class FavouriteEmployerUpdateRequestDto {
    private Long comment; // комментарий. Может быть пустым

    public FavouriteEmployerUpdateRequestDto() {
    }

    public FavouriteEmployerUpdateRequestDto(Long comment) {
        this.comment = comment;
    }

    public Long getComment() {
        return comment;
    }

    public void setComment(Long comment) {
        this.comment = comment;
    }
}
