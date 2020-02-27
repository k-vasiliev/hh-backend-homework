package ru.hh.back.resource;

import ru.hh.back.dao.UserDao;
import ru.hh.back.dto.UserDto;
import ru.hh.back.entity.UserEntity;
import ru.hh.back.service.UserMapper;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/api/user")
public class UserResource {
    private final UserDao userDao;
    private final UserMapper userMapper;

    @Inject
    public UserResource(UserDao userDao, UserMapper userMapper){
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@DefaultValue("applicant") @QueryParam("type") String type) {
        var users = userDao.getUser(type);
        var usersDto = users.stream().map(userMapper::map).collect(Collectors.toList());
        return Response.ok(usersDto).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserDto user) {
        Integer userId = userDao.save(new UserEntity(user.getName(), user.getType()));
        return Response.ok(userId).build();
    }
}

