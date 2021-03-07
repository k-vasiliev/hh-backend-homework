package ru.hh.school.resource;

import ru.hh.school.dto.EmployerDto;
import ru.hh.school.exception.ApiRequestException;
import ru.hh.school.service.EmployerService;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/employer")
public class EmployerResource {

    private final EmployerService employerService;

    public EmployerResource(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployersFromApi(
            @QueryParam("query") String query,
            @QueryParam("page") String page,
            @QueryParam("per_page") String perPage) {
        try {
            List<EmployerDto> employers =  employerService.fetchEmployersFromApi(query, page, perPage);
            return Response.ok().entity(employers).build();
        } catch (ApiRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
