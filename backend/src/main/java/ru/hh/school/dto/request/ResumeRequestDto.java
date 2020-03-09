package ru.hh.school.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResumeRequestDto {
    @NotNull
    private Integer userId;
    @NotNull
    @Size(min = 1, max = 256)
    private String title;
    private String workExperience;
    @NotNull
    private String contacts;

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public String getContacts() {
        return contacts;
    }

}
