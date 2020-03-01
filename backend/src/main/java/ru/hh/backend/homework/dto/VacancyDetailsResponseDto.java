package ru.hh.backend.homework.dto;

import ru.hh.backend.homework.entity.CompanyEntity;

import java.util.Optional;

public class VacancyDetailsResponseDto {
    private Integer vacancyId;
    private String title;
    private Integer salary;
    private String description;
    private String contacts;
    private CompanyEntity company;

    public VacancyDetailsResponseDto(Integer vacancyId, String title, Integer salary,
                                     String description, String contacts, CompanyEntity company) {
        this.vacancyId = vacancyId;
        this.title = title;
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
        this.company = company;
    }
}
