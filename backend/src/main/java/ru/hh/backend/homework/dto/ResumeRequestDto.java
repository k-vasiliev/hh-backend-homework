package ru.hh.backend.homework.dto;

public class ResumeRequestDto {
    private String title;
    private Integer userId;
    private String workExperience;
    private String contacts;

    public ResumeRequestDto() {
    }

    public ResumeRequestDto(String title, Integer userId, String workExperience, String contacts) {
        this.title = title;
        this.userId = userId;
        this.workExperience = workExperience;
        this.contacts = contacts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
