package ru.hh.school.resource;

import ru.hh.school.dao.UserDao;
import ru.hh.school.dto.UserRequestDto;
import ru.hh.school.models.User;
import ru.hh.school.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
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
    public List<User> getList(@QueryParam("userType") Integer userType) {
        return userService.getUsersByType(userType);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(UserRequestDto userDto) {
        userService.saveNew(userDto);
    }
}
