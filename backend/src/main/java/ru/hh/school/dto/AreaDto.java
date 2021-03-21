package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Objects;

public class AreaDto {
    private int id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaDto areaDto = (AreaDto) o;
        return id == areaDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
