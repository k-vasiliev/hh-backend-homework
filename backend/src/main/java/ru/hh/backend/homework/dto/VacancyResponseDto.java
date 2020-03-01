package ru.hh.backend.homework.dto;

import ru.hh.backend.homework.entity.CompanyEntity;

import java.util.Date;
import java.util.Optional;

public class VacancyResponseDto {
    private Integer vacancyId;
    private String title;
    private Date creationDate;
    private CompanyEntity company;

    public VacancyResponseDto() {
    }

    public VacancyResponseDto(Integer vacancyId, String title, Date creationDate, CompanyEntity company) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
