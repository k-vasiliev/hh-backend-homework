package ru.hh.school.resource;

import ru.hh.school.dto.request.UserRequestDto;
import ru.hh.school.dto.response.UserResponseDto;
import ru.hh.school.entity.UserType;
import ru.hh.school.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response getAllUsersByType(@QueryParam("type") String type) {
        if (!type.equalsIgnoreCase("applicant") && !type.equalsIgnoreCase("employer")) {
            throw new IllegalArgumentException("User type is not defined!");
        }
        List<UserResponseDto> usersDto = userService.getUsersDtoByType(UserType.valueOf(type.toUpperCase((Locale.ENGLISH))));
        return Response.ok(usersDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserRequestDto userDto) {
        userService.saveNew(userDto);
        return Response.ok().build();
    }
}
