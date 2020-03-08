package ru.hh.school.controller;

import ru.hh.school.dto.NewUserDto;
import ru.hh.school.dto.UserDto;
import ru.hh.school.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/user")
public class ApiUser {
    private  final UserService userService;

    @Inject
    public ApiUser(UserService userService) {
        this.userService = userService;
    }


    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(NewUserDto userData) {
        Response.ResponseBuilder response;

        try {
            Integer uid = userService.addUser(userData.getName(), userData.isCompany());
            response =  Response.ok(uid);
        } catch (Exception E) {
            response =  Response.status(Response.Status.CONFLICT);
        }

        return response.build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@QueryParam("type") String type) {

        boolean isCompany = type.equals("applicant") ? false : true;
        List<UserDto> users = userService.getUsers(isCompany)
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());

        return Response.ok(users).build();
    }
}
