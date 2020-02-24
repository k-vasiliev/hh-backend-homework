package ru.hh.school.dto.response;

import ru.hh.school.dto.UserDto;

public class ResumeDto {
    private Integer id;
    private String title;
    private UserDto applicant;
    private String dateCreate;

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

    public UserDto getApplicant() {
        return applicant;
    }

    public void setApplicant(UserDto applicant) {
        this.applicant = applicant;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}
