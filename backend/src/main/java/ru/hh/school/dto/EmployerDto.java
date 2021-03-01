package ru.hh.school.dto;

public class EmployerDto {

    private String id;
    private String name;
    private String description;
    private AreaDto areaDto;

    public EmployerDto(String id, String name, String description, AreaDto areaDto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.areaDto = areaDto;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AreaDto getAreaDto() {
        return areaDto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAreaDto(AreaDto areaDto) {
        this.areaDto = areaDto;
    }
}
