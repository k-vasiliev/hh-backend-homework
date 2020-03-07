package ru.hh.nab.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dto.CreateUserDTO;
import ru.hh.nab.dto.ResponseUserDTO;
import ru.hh.nab.entity.User;
import ru.hh.nab.entity.UserType;
import ru.hh.nab.service.UserService;
import ru.hh.nab.util.Utils;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

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
        return userService.getAllUsersResponseByType(Utils.setType(type));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseUserDTO getUserById(@PathParam("id") int id) {
        User user = userService.getUsersById(id);
        return new ResponseUserDTO(user.getUserName(), user.getUserId());
    }

    @POST
    @Consumes(value = "application/json")
    public List<User> createUser(@Valid @RequestBody CreateUserDTO body) {
        List<User> user = Collections.emptyList();
        try {
            user = Collections.singletonList(
                    userService.addUser(body.getName(), body.getType())
            );
            logger.info(String.format(
                    "Create User by Name: %s, Type: %s",
                    body.getName(), body.getType()
            ));
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

}
