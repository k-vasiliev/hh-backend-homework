package ru.hh.school.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployerApi {

    @JsonProperty(required = true)
    private Integer id;

    @JsonProperty(required = true)
    private String name;

    @Override
    public String toString() {
        return "EmployerDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
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
