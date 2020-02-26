package ru.hh.backend.homework.mapper;

import org.springframework.stereotype.Service;
import ru.hh.backend.homework.dto.UserDto;
import ru.hh.backend.homework.entity.UserEntity;

@Service
public class UserMapper {
    public UserDto map(UserEntity userEntity) {
        return new UserDto(userEntity.getId(),
                userEntity.getCreationDate(),
                userEntity.getModificationDate(),
                userEntity.getName(),
                userEntity.getUserType());
    }
}
