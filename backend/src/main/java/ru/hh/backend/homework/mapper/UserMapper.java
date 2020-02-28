package ru.hh.backend.homework.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dto.request.UserRequestDto;
import ru.hh.backend.homework.dto.response.UserResponseDto;
import ru.hh.backend.homework.entity.UserEntity;

import javax.inject.Singleton;

@Service
public class UserMapper {
    public UserEntity map(UserRequestDto userRequestDto) {
        UserEntity user = new UserEntity();
        user.setName(userRequestDto.getName());
        user.setUserType(userRequestDto.getType());
        return  user;
    }

    public UserResponseDto map(UserEntity user) {
        return new UserResponseDto(user.getId(), user.getName());
    }
}
