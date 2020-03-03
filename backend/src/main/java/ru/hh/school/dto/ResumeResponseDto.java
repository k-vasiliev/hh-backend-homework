package ru.hh.school.dto;

public class ResumeResponseDto {

    private Integer id;
    private String title;
    private String dateCreate;
    private UserResponseDto applicant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public UserResponseDto getApplicant() {
        return applicant;
    }

    public void setApplicant(UserResponseDto applicant) {
        this.applicant = applicant;
    }
}
