package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.Objects;

public class VacancyDto {
    private int id;
    private String name;
    private AreaDto areaDto;
    private SalaryDto salaryDto;
    private String createdAt;
    private EmployerDto employerDto;

    public int getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    public AreaDto getAreaDto() {
        return areaDto;
    }

    @JsonSetter("area")
    public void setAreaDto(AreaDto areaDto) {
        this.areaDto = areaDto;
    }

    public SalaryDto getSalaryDto() {
        return salaryDto;
    }

    @JsonSetter("salary")
    public void setSalaryDto(SalaryDto salaryDto) {
        this.salaryDto = salaryDto;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @JsonSetter("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public EmployerDto getEmployerDto() {
        return employerDto;
    }

    @JsonSetter("employer")
    public void setEmployerDto(EmployerDto employerDto) {
        this.employerDto = employerDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyDto that = (VacancyDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
