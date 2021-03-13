package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.dto.area.AreaDto;
import ru.hh.school.dto.company.CompanyDtoRequest;
import ru.hh.school.dto.salary.SalaryDto;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyDtoRequest {
    public enum Popularity {
        POPULAR,
        REGULAR
    }

    private Integer id;
    private String name;
    private LocalDate creationDate;
    private AreaDto area;
    private SalaryDto salary;
    private String createdAt;
    private CompanyDtoRequest employer;
    private Popularity popularity;
    private Integer views_count;
    private String comment;

    public VacancyDtoRequest(Integer id, String name, LocalDate creationDate, AreaDto area, SalaryDto salary, String createdAt, CompanyDtoRequest employer, Popularity popularity, Integer views_count, String comment) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.area = area;
        this.salary = salary;
        this.createdAt = createdAt;
        this.employer = employer;
        this.popularity = popularity;
        this.views_count = views_count;
        this.comment = comment;
    }

    public VacancyDtoRequest() {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public AreaDto getArea() {
        return area;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }

    public SalaryDto getSalary() {
        return salary;
    }

    public void setSalary(SalaryDto salary) {
        this.salary = salary;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public CompanyDtoRequest getEmployer() {
        return employer;
    }

    public void setEmployer(CompanyDtoRequest employer) {
        this.employer = employer;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public Integer getViews_count() {
        return views_count;
    }

    public void setViews_count(Integer views_count) {
        this.views_count = views_count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
