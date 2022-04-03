package ru.hh.school.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class HHVacancyResponseDto {
    private String id;
    private String name;
    private AreaData area;
    private SalaryData salary; // зарплата в том же формате, что в api hh.ru
    @JsonProperty(value = "created_at")
    private Date createdAt;
    private VacancyEmployerResponseDto employer;

    public HHVacancyResponseDto() {
    }

    public HHVacancyResponseDto(String id, String name, AreaData area, SalaryData salary, Date createdAt, VacancyEmployerResponseDto employer) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.salary = salary;
        this.createdAt = createdAt;
        this.employer = employer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaData getArea() {
        return area;
    }

    public void setArea(AreaData area) {
        this.area = area;
    }

    public SalaryData getSalary() {
        return salary;
    }

    public void setSalary(SalaryData salary) {
        this.salary = salary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public VacancyEmployerResponseDto getEmployer() {
        return employer;
    }

    public void setEmployer(VacancyEmployerResponseDto employer) {
        this.employer = employer;
    }
}
