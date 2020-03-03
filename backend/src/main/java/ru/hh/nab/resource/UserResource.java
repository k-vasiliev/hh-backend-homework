package ru.hh.nab.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dto.CreateUserDTO;
import ru.hh.nab.dto.ResponseUserDTO;
import ru.hh.nab.entity.User;
import ru.hh.nab.service.UserService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/user")
@Singleton
public class UserResource {

    private static Logger logger = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResponseUserDTO> getUserByType(@DefaultValue("applicant") @QueryParam("type") String type) {
        return userService.getAllUsersByType(type).stream()
                .map(user -> new ResponseUserDTO(user).build())
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(value = "application/json")
    public User createUser(@Valid @RequestBody CreateUserDTO body) {
        logger.info("Create User");
        return userService.addUser(body.getName(), body.getType());
    }

}
