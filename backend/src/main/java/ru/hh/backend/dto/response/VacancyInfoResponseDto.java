package ru.hh.backend.dto.response;

import lombok.Data;

@Data
public class VacancyInfoResponseDto {

    private Long id;

    private String title;

    private Company company;

    private Long salary;

    private String description;

    private String contacts;

    @Data
    public static class Company {

        private String name;
    }
}
