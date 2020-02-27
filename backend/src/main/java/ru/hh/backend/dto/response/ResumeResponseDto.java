package ru.hh.backend.dto.response;

import ru.hh.backend.entity.UserEntity;

public class ResumeResponseDto {
    private Long id;

    private String title;

    private String dateCreate;

    private UserEntity user;

    public ResumeResponseDto(Long id, String title, String dateCreate, UserEntity user) {
        this.id = id;
        this.title = title;
        this.dateCreate = dateCreate;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
