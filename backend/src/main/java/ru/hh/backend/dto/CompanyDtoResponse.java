package ru.hh.backend.dto;

public class CompanyDtoResponse {
    private Integer id;
    private String name;

    public CompanyDtoResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
