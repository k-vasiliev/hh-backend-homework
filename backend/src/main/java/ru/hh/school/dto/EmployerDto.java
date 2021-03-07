package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployerDto {

    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String name;

    @Override
    public String toString() {
        return "EmployerDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
