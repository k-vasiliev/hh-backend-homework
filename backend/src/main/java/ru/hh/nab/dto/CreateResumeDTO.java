package ru.hh.nab.dto;

import javax.validation.constraints.NotNull;

public class CreateResumeDTO {
    @NotNull
    private String title;
    @NotNull
    private String userId;
    @NotNull
    private String workExperience;
    @NotNull
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
