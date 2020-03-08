package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.entity.CompanyEntity;

public class CompanyDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private Integer id;

    public CompanyDto(CompanyEntity entity) {
        this.name = entity.getCompanyName();
        this.id = entity.getCompanyId();
    }
}
