package ru.hh.school.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class VacanciesResponseData {
    private Long id;
    private String name;
    private String area;
    private Double salary; // зарплата в том же формате, что в api hh.ru
    @JsonProperty(value = "created_at")
    private Date createdAt;
    private Long employer;

    public VacanciesResponseData() {
    }

    public VacanciesResponseData(Long id, String name, String area, Double salary, Date createdAt, Long employer) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.salary = salary;
        this.createdAt = createdAt;
        this.employer = employer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getEmployer() {
        return employer;
    }

    public void setEmployer(Long employer) {
        this.employer = employer;
    }
}
