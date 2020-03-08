package ru.hh.school.mapper;


import ru.hh.school.dto.UserRequestDto;
import ru.hh.school.dto.UserResponseDto;
import ru.hh.school.entity.UserEntity;

import javax.inject.Singleton;

@Singleton
public class UserMapper {
    public UserEntity map(UserRequestDto userRequestDto) {
        UserEntity user = new UserEntity();
        user.setName(userRequestDto.getName());
        user.setType(userRequestDto.getUserType());
        return user;
    }

    public UserResponseDto map(UserEntity userEntity) {
        return new UserResponseDto(userEntity.getId(), userEntity.getName());
    }
}