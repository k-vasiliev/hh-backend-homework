package ru.hh.school.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VacancyResponseDto {
    private String id;
    private String name;
    private String area;
    private String salary; // зарплата в том же формате, что в api hh.ru
    @JsonProperty(value = "created_at")
    private String createdAt;
    private String employer;

    public VacancyResponseDto() {
    }

    public VacancyResponseDto(String id, String name, String area, String salary, String createdAt, String employer) {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }
}
