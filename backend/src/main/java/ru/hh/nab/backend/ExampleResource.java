package ru.hh.nab.backend;

import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dao.UserDAO;
import ru.hh.nab.dto.CreateUserDTO;
import ru.hh.nab.entity.User;

import javax.validation.Valid;
import javax.ws.rs.*;

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


  @POST
  @Path("/user")
  public User createUser(@Valid @RequestBody CreateUserDTO json) {
    return userDAO.addUser(json.getName(), json.getType());
  }

}
