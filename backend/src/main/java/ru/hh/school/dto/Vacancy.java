package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vacancy {

    private Integer id;
    private String name;
    private Area area;
    private Salary salary;
    @JsonProperty("created_at")
    private String createdAt;
    private Employer employer;

    public Vacancy() {
    }

    public Vacancy(Integer id, String name, Area area, Salary salary, String createdAt, Employer employer) {
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

}
