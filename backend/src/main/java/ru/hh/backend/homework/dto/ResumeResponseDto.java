package ru.hh.backend.homework.dto;

import java.time.LocalDate;
import java.util.Date;

public class ResumeResponseDto {
    private String title;
    private Integer resumeId;
    private LocalDate creationDate;

    public ResumeResponseDto() {
    }

    public ResumeResponseDto(String title, Integer resumeId, LocalDate creationDate) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
