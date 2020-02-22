package ru.hh.backend.dto.request;

import lombok.Data;

@Data
public class VacancyRequestDto {

    private String title;

    private Long companyId;

    private Integer salary;

    private String description;

    private String contacts;
}
