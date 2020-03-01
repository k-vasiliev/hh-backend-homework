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

    public VacancyDetailsResponseDto() {
    }

    public VacancyDetailsResponseDto(Integer vacancyId, String title, Integer salary,
                                     String description, String contacts, CompanyEntity company) {
        this.vacancyId = vacancyId;
        this.title = title;
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
