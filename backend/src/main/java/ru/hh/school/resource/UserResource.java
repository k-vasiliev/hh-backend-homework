package ru.hh.school.resource;

import ru.hh.school.dto.UserRequestDto;
import ru.hh.school.entity.User;
import ru.hh.school.entity.UserType;
import ru.hh.school.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/user")
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getList(@QueryParam("userType") UserType userType) {
        return userService.getUsersByType(userType);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(UserRequestDto userDto) {
        userService.saveNew(userDto);
    }
}
