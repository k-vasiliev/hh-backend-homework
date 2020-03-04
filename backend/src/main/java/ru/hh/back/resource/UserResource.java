package ru.hh.back.resource;

import ru.hh.back.dto.UserDto;
import ru.hh.back.service.UserService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/user")
public class UserResource {
    private final UserService userService;

    //@Inject
    public UserResource(UserService userDao) {
        this.userService = userDao;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@DefaultValue("applicant") @QueryParam("type") String type) {
        var users = userService.getUsers(type);
        return Response.ok(users).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserDto user) {
        Integer userId = userService.saveUser(user);
        return Response.ok(userId).build();
    }
}

