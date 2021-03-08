package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.service.ApiService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/employer")
public class EmployerResource {

    private static final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    private final ApiService apiService;

    public EmployerResource(ApiService apiService) {
        this.apiService = apiService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployersFromApi (
            @QueryParam("query") String query,
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("20") @QueryParam("per_page") Integer perPage
    ) {
        try {
            List<EmployerDto> employers = apiService.fetchEmployersFromApi(query, page, perPage);
            return Response.ok().entity(employers).build();
        } catch (WebApplicationException exception) {
            throw new WebApplicationException(exception.getMessage(), exception.getResponse().getStatus());
        }
    }

    @GET
    @Path("/{employer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployerFromApiById(@PathParam("employer_id") Integer employerId) {
        try {
            EmployerDto employer = apiService.fetchEmployersFromApiById(employerId);
            return Response.ok().entity(employer).build();
        } catch (WebApplicationException exception) {
            throw new WebApplicationException(exception.getMessage(), exception.getResponse().getStatus());
        }
    }

}
