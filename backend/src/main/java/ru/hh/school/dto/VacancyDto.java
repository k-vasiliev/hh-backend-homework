package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.entity.EmployerApi;

public class VacancyDto {

    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private AreaDto area;

    @JsonProperty(required = true)
    private SalaryDto salary;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty(required = true)
    private EmployerApi employer;

    public String getCreatedAt() {
        return createdAt;
    }
}
