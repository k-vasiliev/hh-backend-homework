package ru.hh.school.resource;


import ru.hh.school.UserType;
import ru.hh.school.dto.UserRequestDto;
import ru.hh.school.dto.UserResponseDto;
import ru.hh.school.mapper.UserMapper;
import ru.hh.school.service.UserService;

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


    public UserResource(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserResponseDto create(UserRequestDto userRequestDto) {
        return userMapper.map(userService.create(userMapper.map(userRequestDto)));
    }

    @GET
    public List<UserResponseDto> get(@QueryParam("type") UserType userType) {
        return userService.getAll().stream().map(userMapper::map).collect(Collectors.toList());
    }
}
