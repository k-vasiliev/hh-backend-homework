package ru.hh.school.dto;

import java.time.LocalDate;



public class ResumeResponseDto {
    private String title;
    private Long resumeId;
    private LocalDate creationDate;

    public ResumeResponseDto(String title, Long resumeId, LocalDate creationTime) {
        this.title = title;
        this.resumeId = resumeId;
        this.creationDate = creationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
