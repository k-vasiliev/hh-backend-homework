package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewVacancyDto {
    @JsonProperty("title")
    public String vacancyTtile;

    @JsonProperty("companyId")
    public Integer companyId;

    @JsonProperty("salary")
    public Integer salary;

    @JsonProperty("description")
    public String description;

    @JsonProperty("contacts")
    public String contacts;
}
