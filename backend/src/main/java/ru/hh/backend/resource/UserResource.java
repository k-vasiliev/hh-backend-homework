package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.dto.request.UserRequestDto;
import ru.hh.backend.dto.response.UserResponseDto;
import ru.hh.backend.mapper.UserMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class UserResource {

    private final UserMapper userMapper;
    private final UserDao userDao;

    public UserResource(UserMapper userMapper, UserDao userDao) {
        this.userMapper = userMapper;
        this.userDao = userDao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserResponseDto create(UserRequestDto userRequestDto) {
        return userMapper.map(userDao.create(userMapper.map(userRequestDto)));
    }

    @GET
    public List<UserResponseDto> get(@QueryParam("type") String type) {
        return userDao.getByType(type).stream().map(userMapper::map).collect(Collectors.toList());
    }
}
