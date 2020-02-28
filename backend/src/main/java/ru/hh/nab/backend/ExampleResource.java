package ru.hh.nab.backend;

import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dao.UserDAO;
import ru.hh.nab.dto.CreateUserDTO;
import ru.hh.nab.entity.Users;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
public class ExampleResource {

  private final UserDAO userDAO;

  public ExampleResource(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  /*@GET
  @Path("/resume")
  public String hello(@DefaultValue("world") @QueryParam("name") String name) {
    return String.format("Hello, %s!", name);
  }*/

  @GET
  @Path("/user")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Users> hello() {
    List<Users> users = userDAO.getAllUsers();

    return users;
  }


  @POST
  @Path("/user")
  @Consumes(value = "application/json")
  public Users createUser(@Valid @RequestBody CreateUserDTO body) {
    return userDAO.addUser(body.getName(), body.getType());
  }

}
