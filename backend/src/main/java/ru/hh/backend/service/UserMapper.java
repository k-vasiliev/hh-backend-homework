package ru.hh.backend.service;

import org.springframework.stereotype.Service;
import ru.hh.backend.dto.request.UserRequestDto;
import ru.hh.backend.dto.response.UserResponseDto;
import ru.hh.backend.entity.User;

@Service
public class UserMapper {

    public User map(UserRequestDto userRequestDto) {
        return new User(userRequestDto.getName(), userRequestDto.getType());
    }

    public UserResponseDto map(User user) {
        return new UserResponseDto(user.getId(), user.getName());
    }
}
