package ru.hh.school.resource;

import ru.hh.school.dto.EmployerItemsApi;
import ru.hh.school.dto.response.ErrorResponseDto;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.service.EmployerService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;

@Path("/employer")
@Singleton
public class EmployerResource {

    private final EmployerService employerService;

    public EmployerResource(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployers(@QueryParam("page") String page,
                                 @QueryParam("per_page") String perPage,
                                 @QueryParam("query") String query) {
        EmployerItemsApi employers = null;
        try {
            employers = employerService.getEmployers(page, perPage, query);
        } catch (HhRequestException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }

        return Response
                .ok()
                .build();
    }

    @GET
    @Path(value = "/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployerById(@PathParam(value = "id") Integer id) throws InterruptedException, IOException, URISyntaxException {
       //EmployerService.getEmployerById(String.valueOf(id))
        return Response
                .ok()
                .entity(employerService.get(id))
                .build();
    }
}
