package ru.hh.school.dto;

public class EmployerDTO {

    private Integer id;
    private String name;
    private String description;
    private AreaDTO area;

    public EmployerDTO(Integer id, String name, String description, AreaDTO area) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AreaDTO getArea() {
        return area;
    }

    public void setArea(AreaDTO area) {
        this.area = area;
    }
}
