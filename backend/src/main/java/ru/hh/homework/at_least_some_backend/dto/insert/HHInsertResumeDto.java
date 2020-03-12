package ru.hh.homework.at_least_some_backend.dto.insert;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.homework.at_least_some_backend.entity.HHResume;

public class HHInsertResumeDto extends InsertDto<HHResume>
{
    private String title;
    private Long userId;
    @JsonProperty("workExperience")
    private String experience;
    private String contacts;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getContacts() { return contacts; }
    public void setContacts(String contacts) { this.contacts = contacts; }
}
