package ru.hh.backend.dto;

import java.time.LocalDate;

public class ResumeDtoResponse {
    private Integer id;
    private String title;
    private String dateCreate;
    private UserDtoResponse applicant;

    public ResumeDtoResponse(Integer id, UserDtoResponse user, String title, String dateCreate) {
        this.id = id;
        this.applicant = user;
        this.title = title;
        this.dateCreate = dateCreate;
    }

    public Integer getId() {
        return id;
    }

    public UserDtoResponse getApplicant() {
        return applicant;
    }

    public String getTitle() {
        return title;
    }

    public String getDateCreate() {
        return dateCreate;
    }
}
