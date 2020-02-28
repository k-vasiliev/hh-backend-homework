package ru.hh.backend.homework.dto.response;

import ru.hh.backend.homework.entity.UserEntity;

public class ResumeResponseDto {
    private Long id;

    private String title;

    private String dateCreate;

    private UserEntity applicant;

    public ResumeResponseDto(Long id, String title, String dateCreate, UserEntity user) {
        this.id = id;
        this.title = title;
        this.dateCreate = dateCreate;
        this.applicant = user;
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

    public UserEntity getApplicant() {
        return applicant;
    }

    public void setApplicant(UserEntity applicant) {
        this.applicant = applicant;
    }
}
