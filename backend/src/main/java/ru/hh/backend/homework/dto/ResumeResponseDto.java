package ru.hh.backend.homework.dto;

import java.util.Date;

public class ResumeResponseDto {
    private String title;
    private Integer resumeId;
    private Date creationDate;

    public ResumeResponseDto() {
    }

    public ResumeResponseDto(String title, Integer resumeId, Date creationDate) {
        this.title = title;
        this.resumeId = resumeId;
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
