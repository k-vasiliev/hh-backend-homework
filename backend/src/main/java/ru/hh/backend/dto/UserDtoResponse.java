package ru.hh.backend.dto;

public class UserDtoResponse {
    private Integer id;
    private String name;

    public UserDtoResponse(Integer id, String name) {
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
