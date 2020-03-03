package ru.hh.nab.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateResumeDTO {

    @NotNull
    @Size(min = 1, max = 250, message = "Resume title must be between 1 and 250 characters")
    private String title;

    @NotNull
    private String userId;

    @NotNull
    @Size(min = 1, max = 250, message = "Resume workExperience must be between 1 and 250 characters")
    private String workExperience;

    @NotNull
    @Size(min = 1, max = 250, message = "Resume contacts must be between 1 and 250 characters")
    private String contacts;

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return userId;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public String getContacts() {
        return contacts;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
