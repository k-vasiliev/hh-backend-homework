package ru.hh.school.resource.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.hh.school.utils.LocalDateTimeDeserializer;
import ru.hh.school.utils.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Date;

public class HHVacancyResponseDto {
    private Long id;
    private String name;
    private AreaData area;
    private SalaryData salary; // зарплата в том же формате, что в api hh.ru
//    @JsonProperty(value = "created_at")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    private LocalDateTime createdAt;
    private VacancyEmployerResponseDto employer;

    public HHVacancyResponseDto() {
    }

    public HHVacancyResponseDto(Long id, String name, AreaData area, SalaryData salary, LocalDateTime createdAt, VacancyEmployerResponseDto employer) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.salary = salary;
//        this.createdAt = createdAt;
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

//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }

    public VacancyEmployerResponseDto getEmployer() {
        return employer;
    }

    public void setEmployer(VacancyEmployerResponseDto employer) {
        this.employer = employer;
    }
}
