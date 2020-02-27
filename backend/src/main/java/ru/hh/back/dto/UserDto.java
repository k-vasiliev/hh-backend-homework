package ru.hh.back.dto;

public class UserDto {
    private Integer userId;
    private String name;
    private String type;
    public UserDto() {
    }

    public UserDto(Integer userId, String name, String type) {
        this.name = name;
        this.type = type;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
