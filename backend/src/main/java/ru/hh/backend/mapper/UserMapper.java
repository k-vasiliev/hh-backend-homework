package ru.hh.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.hh.backend.dto.request.UserRequestDto;
import ru.hh.backend.dto.response.UserResponseDto;
import ru.hh.backend.model.User;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface UserMapper {

    @Mapping(target="userType", expression = "java(userRequestDto.getType().toLowerCase())")
    User map(UserRequestDto userRequestDto);

    UserResponseDto map(User user);
}
