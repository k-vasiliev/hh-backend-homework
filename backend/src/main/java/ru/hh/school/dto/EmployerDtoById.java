package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployerDtoById extends EmployerDto {

    protected String description;
    protected AreaDto area;

    public EmployerDtoById() {}

    public EmployerDtoById(int id, String name, String description, AreaDto area) {
        super(id, name);
        this.description = description;
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AreaDto getArea() {
        return area;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "EmployerDtoById[" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description  +
                ", area=" + area +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployerDtoById)) return false;
        EmployerDtoById that = (EmployerDtoById) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(area, that.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, area);
    }
}
