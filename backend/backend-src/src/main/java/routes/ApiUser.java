package routes;

import com.fasterxml.jackson.annotation.JsonProperty;
import dto.UserDto;
import service.ResumeService;
import service.UserService;

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

    static class CreateUserData {
        @JsonProperty("name")
        private String name;

        @JsonProperty("type")
        private String type;

        boolean isCompany() {
            return type.equals("APPLICANT") ? false : true;
        }
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserData userData) {
        Response.ResponseBuilder response;

        try {
            userService.addUser(userData.name, userData.isCompany());
            response =  Response.ok("OK");
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
