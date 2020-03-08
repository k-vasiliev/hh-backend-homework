package ru.hh.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResumeDtoRequest {
    @NotNull
    @Size(min = 1, max = 150)
    private String title;
    @NotNull
    private Integer userId;
    @NotNull
    @Size(min = 1, max = 250)
    private String workExperience;
    @NotNull
    @Size(min = 6, max = 50)
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
