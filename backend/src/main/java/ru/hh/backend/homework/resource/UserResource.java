package ru.hh.backend.homework.resource;

import ru.hh.backend.homework.dto.UserRequestDto;
import ru.hh.backend.homework.dto.UserResponseDto;
import ru.hh.backend.homework.enums.UserType;
import ru.hh.backend.homework.mapper.UserMapper;
import ru.hh.backend.homework.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class UserResource {
    private final UserService userService;
    private final UserMapper userMapper;

    @Inject
    public UserResource(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserResponseDto create(UserRequestDto userRequestDto) {
        return userMapper.map(userService.save(userMapper.map(userRequestDto)));
    }

    @GET
    public List<UserResponseDto> get(@QueryParam("type") UserType userType) {
        return userService.getAllByType(userType).stream().map(userMapper::map).collect(Collectors.toList());
    }
}
