package ru.hh.back.service;

import org.springframework.stereotype.Service;
import ru.hh.back.dto.UserDto;
import ru.hh.back.entity.UserEntity;

@Service
public class UserMapper {

    public UserDto map(UserEntity userEntity) {
        return new UserDto(userEntity.getId(), userEntity.getName(), userEntity.getType());
    }
}
