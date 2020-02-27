package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.dto.request.UserRequestDto;
import ru.hh.backend.dto.response.UserResponseDto;
import ru.hh.backend.service.UserMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;


@Path("/user")
/*@Produces(MediaType.APPLICATION_JSON)*/
/*@Controller*/
public class UserController {
    private final UserMapper userMapper;
    private final UserDao userDao;

    @Inject
    public UserController(UserMapper userMapper, UserDao userDao) {
        this.userMapper = userMapper;
        this.userDao = userDao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserResponseDto create(UserRequestDto userRequestDto) {
        return userMapper.map(userDao.create(userMapper.map(userRequestDto)));
    }

    @GET
    public  Response get(@QueryParam("type") String type) {
        return Response
                .ok(userDao.getByType(type).stream().map(userMapper::map).collect(Collectors.toList()))
                .build();

  /*      return Response.ok(resumeMapper.map(resume)).build();
        return userDao.getByType(type).stream().map(userMapper::map).collect(Collectors.toList());*/
    }
}
