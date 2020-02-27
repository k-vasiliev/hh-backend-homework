package ru.hh.backend.dto.response;

import ru.hh.backend.entity.Company;

public class VacancyResponseDto {
    private Long id;

    private String title;

    private Company company;

    private Integer compensation;

    private String description;

    private String contacts;

    public VacancyResponseDto(Long id, String title, Company company, Integer compensation, String description, String contacts) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.compensation = compensation;
        this.description = description;
        this.contacts = contacts;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getCompensation() {
        return compensation;
    }

    public void setCompensation(Integer compensation) {
        this.compensation = compensation;
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

}
