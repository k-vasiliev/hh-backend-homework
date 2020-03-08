package ru.hh.backend.resource;

import ru.hh.backend.dto.UserDtoRequest;
import ru.hh.backend.mapper.UserMapper;
import ru.hh.backend.service.UserService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class UserResource {

    private UserService userService;
    private UserMapper userMapper;

    public UserResource(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GET
    public Response getUsersByType(@QueryParam("type") String type) {
        return Response.ok(
                userService.getUsersByType(type).stream().map(userMapper::map).collect(Collectors.toList())
        ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid UserDtoRequest userDtoRequest) {
        return Response.ok(
                userService.save(userMapper.map(userDtoRequest))
        ).build();
    }

}
