package ru.hh.backend.homework.dto.request;

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
}
