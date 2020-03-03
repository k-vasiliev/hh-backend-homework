package dto;

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

    public String getExperience() {
        return workExperience;
    }

    public Integer getUserId() {
        return userId;
    }
}
