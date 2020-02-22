package ru.hh.backend.dto.response;

import lombok.Data;

@Data
public class VacancyResponseDto {

    private Long id;

    private String title;

    private String dateCreate;

    private Company company;

    @Data
    public static class Company {

        private String name;
    }
}
