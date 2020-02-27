package ru.hh.backend.homework.dto;

public class VacancyRequestDto {
    private String title;
    private Integer companyId;
    private Integer salary;
    private String description;
    private String contacts;

    public VacancyRequestDto(String title, Integer companyId, Integer salary, String description, String contacts) {
        this.title = title;
        this.companyId = companyId;
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
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
