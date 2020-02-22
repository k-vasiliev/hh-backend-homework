package ru.hh.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.hh.backend.dto.request.UserRequestDto;
import ru.hh.backend.dto.response.UserResponseDto;
import ru.hh.backend.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings(@Mapping(target="userType", expression = "java(userRequestDto.getType().toLowerCase())"))
    User map(UserRequestDto userRequestDto);

    @Mappings(@Mapping(target = "dateCreate", dateFormat = "dd.MM.yyyy"))
    UserResponseDto map(User user);
}
