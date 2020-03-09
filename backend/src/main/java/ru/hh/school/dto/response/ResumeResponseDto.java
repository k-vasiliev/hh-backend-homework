package ru.hh.school.dto.response;

public class ResumeResponseDto {

    private Integer id;
    private String title;
    private String dateCreate;
    private UserResponseDto applicant;

    public ResumeResponseDto(Integer id, String title, String dateCreate, UserResponseDto applicant) {
        this.id = id;
        this.title = title;
        this.dateCreate = dateCreate;
        this.applicant = applicant;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public UserResponseDto getApplicant() {
        return applicant;
    }

}
