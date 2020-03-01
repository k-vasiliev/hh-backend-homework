package ru.hh.backend.homework.mapper;

import ru.hh.backend.homework.dto.UserRequestDto;
import ru.hh.backend.homework.dto.UserResponseDto;
import ru.hh.backend.homework.entity.UserEntity;

import javax.inject.Singleton;

@Singleton
public class UserMapper {
    public UserEntity map(UserRequestDto userRequestDto) {
        return new UserEntity(userRequestDto.getName(), userRequestDto.getUserType());
    }

    public UserResponseDto map(UserEntity userEntity) {
        return new UserResponseDto(userEntity.getUserId(), userEntity.getName());
    }
}
