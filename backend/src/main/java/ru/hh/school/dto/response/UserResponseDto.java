package ru.hh.school.dto.response;

public class UserResponseDto {

    private String name;
    private Integer id;

    public UserResponseDto(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

}
