package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.hh.school.serialize.OffsetDateTimeDeserializer;
import ru.hh.school.serialize.OffsetDateTimeSerializer;

import java.time.OffsetDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyDto {

    protected int id;

    protected String name;

    protected AreaDto area;

    protected SalaryDto salary;

    @JsonProperty("created_at")
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    protected OffsetDateTime createdAt;

    protected EmployerDto employer;

    public VacancyDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public EmployerDto getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerDto employer) {
        this.employer = employer;
    }

    @Override
    public String toString() {
        return "VacancyDto[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", salary=" + salary +
                ", createdAt=" + createdAt +
                ", employer=" + employer +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VacancyDto) || o == null) return false;
        VacancyDto that = (VacancyDto) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(area, that.area) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(createdAt, that.createdAt);
    }

}
