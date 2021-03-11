package ru.hh.school.resource;

import ru.hh.school.dto.EmployerApiHh;
import ru.hh.school.dto.EmployerItemsApiHh;
import ru.hh.school.dto.response.ErrorResponseDto;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.service.ApiHhService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employer")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class EmployerResource {

    private final ApiHhService apiHhService;

    public EmployerResource(ApiHhService apiHhService) {
        this.apiHhService = apiHhService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployers(@QueryParam("page") Integer page,
                                 @QueryParam("per_page") Integer perPage,
                                 @QueryParam("query") String query) {
        EmployerItemsApiHh employers;
        try {
            employers = apiHhService.getEmployers(query, page, perPage);
        } catch (HhRequestException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }

        return Response
                .ok()
                .entity(employers)
                .build();
    }

    @GET
    @Path(value = "/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployerById(@PathParam(value = "id") Integer id) {
        EmployerApiHh employer;
        try {
            employer = apiHhService.getEmployerBy(id);
        } catch (HhRequestException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }

        return Response
                .ok()
                .entity(employer)
                .build();
    }

}
