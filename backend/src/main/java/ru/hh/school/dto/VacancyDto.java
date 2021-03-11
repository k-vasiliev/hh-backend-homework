package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class VacancyDto {

    @JsonProperty(required = true)
    private Integer id;

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private AreaDto area;

    @JsonProperty(required = true)
    private SalaryDto salary;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty(required = true)
    private EmployerApiHh employer;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AreaDto getArea() {
        return area;
    }

    public SalaryDto getSalary() {
        return salary;
    }

    public EmployerApiHh getEmployer() {
        return employer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }

    public void setSalary(SalaryDto salary) {
        this.salary = salary;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setEmployer(EmployerApiHh employer) {
        this.employer = employer;
    }
}
