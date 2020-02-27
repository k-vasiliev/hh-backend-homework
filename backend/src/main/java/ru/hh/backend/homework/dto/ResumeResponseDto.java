package ru.hh.backend.homework.dto;

import java.util.Date;

public class ResumeResponseDto {
    private String title;
    private Integer resumeId;
    private Date creationDate;

    public ResumeResponseDto(String title, Integer resumeId, Date creationDate) {
        this.title = title;
        this.resumeId = resumeId;
        this.creationDate = creationDate;
    }
}
