package ru.hh.school.dto;

import ru.hh.school.entity.UserType;

public class UserResponseDto {

    private String name;
    private Integer id;
//    private UserType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public UserType getType() {
//        return type;
//    }
//
//    public void setType(UserType type) {
//        this.type = type;
//    }
}
