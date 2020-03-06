package ru.hh.school.resource;

import ru.hh.school.dto.request.UserRequestDto;
import ru.hh.school.dto.response.UserResponseDto;
import ru.hh.school.entity.UserType;
import ru.hh.school.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Locale;

@Path("/api/user")
public class UserResource {

    private UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResponseDto> getAllUsersByType(@QueryParam("type") String type) {
        return userService.getUsersByType(UserType.valueOf(type.toUpperCase((Locale.ENGLISH))));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(UserRequestDto userDto) {
        userService.saveNew(userDto);
    }
}
