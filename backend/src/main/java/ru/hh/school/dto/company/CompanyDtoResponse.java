package ru.hh.school.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDtoResponse {
    private Integer id;
    private String name;

    public CompanyDtoResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyDtoResponse() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CompanyDtoResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
