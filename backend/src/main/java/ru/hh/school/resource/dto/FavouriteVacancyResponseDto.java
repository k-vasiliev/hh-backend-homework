package ru.hh.school.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

public class FavouriteVacancyResponseDto {
    private String id;
    private String name;
    private AreaData area;
    private SalaryData salary; // зарплата в том же формате, что в api hh.ru
    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;
    private VacancyEmployerResponseDto employer;
    private String comment;
    private Long viewsCount;
    private String popularity;
    private LocalDateTime dateCreate;

    public FavouriteVacancyResponseDto() {
    }

    public FavouriteVacancyResponseDto(String id, String name, AreaData area, SalaryData salary, LocalDateTime createdAt, VacancyEmployerResponseDto employer, String comment, Long viewsCount, String popularity, LocalDateTime dateCreate) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.salary = salary;
        this.createdAt = createdAt;
        this.employer = employer;
        this.comment = comment;
        this.viewsCount = viewsCount;
        this.popularity = popularity;
        this.dateCreate = dateCreate;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public VacancyEmployerResponseDto getEmployer() {
        return employer;
    }

    public void setEmployer(VacancyEmployerResponseDto employer) {
        this.employer = employer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }
}
