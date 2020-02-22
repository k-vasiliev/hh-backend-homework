package ru.hh.backend.dto.response;

import lombok.Data;

@Data
public class ResumeResponseDto {

    private Long id;

    private String title;

    private String dateCreate;

    private Applicant applicant;

    @Data
    public static class Applicant {

        private String name;
    }
}
