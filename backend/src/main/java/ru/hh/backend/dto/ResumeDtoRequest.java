package ru.hh.backend.dto;

public class ResumeDtoRequest {

    private String title;
    private Integer userId;
    private String workExperience;
    private String contacts;

    public ResumeDtoRequest() {
    }

    public String getTitle() {
        return title;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public String getContacts() {
        return contacts;
    }
}
