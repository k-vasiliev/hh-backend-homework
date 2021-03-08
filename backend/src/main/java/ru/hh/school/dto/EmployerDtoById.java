package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

}
