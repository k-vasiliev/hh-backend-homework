package ru.hh.backend.dto.request;

import lombok.Data;

@Data
public class ResumeRequestDto {

    private String title;

    private Long userId;

    private String workExperience;

    private String contacts;
}
