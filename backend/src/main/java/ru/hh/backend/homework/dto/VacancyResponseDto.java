package ru.hh.backend.homework.dto;

import ru.hh.backend.homework.entity.CompanyEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class VacancyResponseDto {
    private Integer vacancyId;
    private String title;
    private LocalDate creationDate;
    private CompanyResponseDto company;

    public VacancyResponseDto() {
    }

    public VacancyResponseDto(Integer vacancyId, String title, LocalDate creationDate, CompanyResponseDto company) {
        this.vacancyId = vacancyId;
        this.title = title;
        this.creationDate = creationDate;
        this.company = company;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public CompanyResponseDto getCompany() {
        return company;
    }

    public void setCompany(CompanyResponseDto company) {
        this.company = company;
    }
}
