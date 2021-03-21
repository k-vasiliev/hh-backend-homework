package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VacancyDTO {

    private Integer id;
    private String name;
    private AreaDTO area;
    private SalaryDTO salary;
    @JsonProperty("created_at")
    private String createdAt;
    private EmployerDTO employer;

    public VacancyDTO(Integer id, String name, AreaDTO area, SalaryDTO salary, String createdAt, EmployerDTO employer) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.salary = salary;
        this.createdAt = createdAt;
        this.employer = employer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaDTO getArea() {
        return area;
    }

    public void setArea(AreaDTO area) {
        this.area = area;
    }

    public SalaryDTO getSalary() {
        return salary;
    }

    public void setSalary(SalaryDTO salary) {
        this.salary = salary;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public EmployerDTO getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerDTO employer) {
        this.employer = employer;
    }
}
