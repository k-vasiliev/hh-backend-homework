package ru.hh.school.resource;

import ru.hh.school.dto.UserDto;
import ru.hh.school.entity.UserType;
import ru.hh.school.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static ru.hh.school.Utils.check;

@Path("/user")
public class UserResource {

    private UserService userService;

    @Inject
    public UserResource(UserService userService) { this.userService = userService;}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(UserDto userDto) {
        check(userDto.getName() != null);
        check(!userDto.getName().isEmpty());
        check((userDto.getName().length()<64));
        userService.createUser(userDto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDto> getUsers(@QueryParam("type") UserType userType) {
        return userService.getUsers(userType);
    }
}
