package ru.hh.backend.dto;

public class VacancyDtoResponse {

    private Integer id;
    private String title;
    private Integer salary;
    private String description;
    private String contacts;
    private String dateCreate;
    private CompanyDtoResponse company;

    public VacancyDtoResponse(Integer id, String title, Integer salary, String description, String contacts, String dateCreate, CompanyDtoResponse company) {
        this.id = id;
        this.title = title;
        this.salary = salary;
        this.description = description;
        this.contacts = contacts;
        this.dateCreate = dateCreate;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public String getDateCreate() {
        return dateCreate;
    }

    public CompanyDtoResponse getCompany() {
        return company;
    }
}
