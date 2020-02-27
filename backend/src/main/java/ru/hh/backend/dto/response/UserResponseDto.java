package ru.hh.backend.dto.response;

public class UserResponseDto {

    private Long id;

    private String name;

    public UserResponseDto(Long id, String userName) {
        this.id = id;
        this.name = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
