package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.entity.UsersEntity;

public class UserDto {
    @JsonProperty("name")
    private String name;


    @JsonProperty("id")
    private Integer id;

    public UserDto(UsersEntity entity) {
        this.name = entity.getName();
        this.id = entity.getId();
    }

    public UserDto(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean equals(UserDto x) {
        return x.name.equals(this.name) && x.id == this.id;
    }

    public UserDto() {}

}
