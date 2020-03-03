package ru.hh.nab.dto;

import ru.hh.nab.entity.User;

public class ResponseUserDTO {

    private final User user;

    private String name;

    private Integer userId;

    public ResponseUserDTO(User user) {
        this.user = user;
    }

    public ResponseUserDTO build() {
        this.name = this.user.getUserName();
        this.userId = this.user.getUserId();
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
