package ru.hh.backend.service;

import org.springframework.stereotype.Service;
import ru.hh.backend.dto.request.UserRequestDto;
import ru.hh.backend.dto.response.UserResponseDto;
import ru.hh.backend.entity.UserEntity;

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
