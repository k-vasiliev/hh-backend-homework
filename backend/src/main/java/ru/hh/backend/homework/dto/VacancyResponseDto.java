package ru.hh.backend.homework.dto;

import ru.hh.backend.homework.entity.CompanyEntity;

import java.util.Date;
import java.util.Optional;

public class VacancyResponseDto {
    private Integer vacancyId;
    private String title;
    private Date creationDate;
    private Optional<CompanyEntity> company;

    public VacancyResponseDto(Integer vacancyId, String title, Date creationDate, Optional<CompanyEntity> company) {
        this.vacancyId = vacancyId;
        this.title = title;
        this.creationDate = creationDate;
        this.company = company;
    }
}
