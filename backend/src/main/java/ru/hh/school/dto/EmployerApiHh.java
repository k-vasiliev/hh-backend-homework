package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployerApiHh {

    @JsonProperty(required = true)
    private Integer id;

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private String description;

    @JsonProperty(required = true)
    private AreaDto area;

    public EmployerApiHh(Integer id, String name, String description, AreaDto area) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
    }

    public EmployerApiHh() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AreaDto getArea() {
        return area;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }
}
