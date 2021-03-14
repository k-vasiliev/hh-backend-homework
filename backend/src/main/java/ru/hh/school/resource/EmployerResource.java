package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.service.ApiService;
import ru.hh.school.component.EmployerMapper;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/employer")
public class EmployerResource {

    private static final Logger logger = LoggerFactory.getLogger(EmployerResource.class);

    private final ApiService apiService;
    private final EmployerMapper employerMapper;

    public EmployerResource(ApiService apiService, EmployerMapper employerMapper) {
        this.apiService = apiService;
        this.employerMapper = employerMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployersFromApi (
            @DefaultValue("") @QueryParam("query") String query,
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("20") @QueryParam("per_page") Integer perPage
    ) {
        try {
            String dataFromApi = apiService.fetchEmployersFromApi(query, page, perPage);
            List<EmployerDto> employers = employerMapper.mapListOfItemsFromApi(dataFromApi);
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
            String dataFromApi = apiService.fetchEmployersFromApiById(employerId);
            EmployerDto employer = employerMapper.mapSingleItemFromApiToDto(dataFromApi);
            return Response.ok().entity(employer).build();
        } catch (WebApplicationException exception) {
            throw new WebApplicationException(exception.getMessage(), exception.getResponse().getStatus());
        }
    }

}
