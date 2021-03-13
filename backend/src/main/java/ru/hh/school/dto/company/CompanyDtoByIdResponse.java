package ru.hh.school.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.dto.area.AreaDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDtoByIdResponse {
    private Integer id;
    private String name;
    private String description;
    private AreaDto area;

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

    public CompanyDtoByIdResponse(Integer id, String name, String description, AreaDto area) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
    }

    public CompanyDtoByIdResponse() {
    }

    @Override
    public String toString() {
        return "CompanyDtoByIdResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", area=" + area +
                '}';
    }
}
