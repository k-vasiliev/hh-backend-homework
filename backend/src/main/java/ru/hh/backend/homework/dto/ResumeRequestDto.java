package ru.hh.backend.homework.dto.request;

public class ResumeRequestDto {
    private String title;
    private Integer userId;
    private String workExperience;
    private String contacts;

    public ResumeRequestDto(String title, Integer userId, String workExperience, String contacts) {
        this.title = title;
        this.userId = userId;
        this.workExperience = workExperience;
        this.contacts = contacts;
    }
}
