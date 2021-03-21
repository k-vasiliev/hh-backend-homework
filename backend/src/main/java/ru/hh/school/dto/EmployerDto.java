package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Objects;

public class EmployerDto {
    private Integer id;
    private String name;
    private String description;
    private AreaDto areaDto;

    public Integer getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public AreaDto getAreaDto() {
        return areaDto;
    }

    @JsonSetter("area")
    public void setAreaDto(AreaDto areaDto) {
        this.areaDto = areaDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployerDto that = (EmployerDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
