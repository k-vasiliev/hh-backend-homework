package ru.hh.backend.dto;

public class VacancyDtoRequest {

    private String title;
    private Integer companyId;
    private Integer salary;
    private String description;
    private String contacts;

    public VacancyDtoRequest() {
    }

    public String getTitle() {
        return title;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }
}
