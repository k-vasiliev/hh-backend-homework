package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AreaDto {

    @JsonProperty(required = true)
    private Integer id;
    @JsonProperty(required = true)
    private String name;

    public AreaDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
