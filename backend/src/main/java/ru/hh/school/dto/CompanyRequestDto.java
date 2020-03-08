package ru.hh.school.dto;

import ru.hh.school.entity.UserEntity;

public class CompanyRequestDto {
    private String name;
    private UserEntity userEntity;

    public CompanyRequestDto(String name, UserEntity userEntity) {
        this.name = name;
        this.userEntity = userEntity;
    }


    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
