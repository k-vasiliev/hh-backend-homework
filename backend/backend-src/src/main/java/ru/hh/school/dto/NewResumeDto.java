package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewResumeDto {
    @JsonProperty("contacts")
    private String contacts;
    @JsonProperty("title")
    private String title;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("workExperience")
    private String workExperience;

    public String getContacts() {
        return contacts;
    }

    public String getTitle() {
        return title;
    }

    @JsonIgnore
    public String getExperience() {
        return workExperience;
    }

    public Integer getUserId() {
        return userId;
    }

    public NewResumeDto(String contacts, String title, Integer userId, String workExperience) {
        this.contacts = contacts;
        this.title = title;
        this.userId = userId;
        this.workExperience = workExperience;
    }


    public NewResumeDto() {}
}
