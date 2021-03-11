package ru.hh.school.resource;

import ru.hh.school.dto.VacancyDto;
import ru.hh.school.dto.VacancyItemsApiHh;
import ru.hh.school.dto.response.ErrorResponseDto;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.service.ApiHhService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class VacancyResource {

    private final ApiHhService apiHhService;

    public VacancyResource(ApiHhService apiHhService) {
        this.apiHhService = apiHhService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getVacancies(@QueryParam("page") Integer page,
                                 @QueryParam("per_page") Integer perPage,
                                 @QueryParam("query") String query) {

        VacancyItemsApiHh vacancyItemsApiHh;
        try {
            vacancyItemsApiHh = apiHhService.getVacancies(query, page, perPage);
        } catch (HhRequestException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }

        return Response
                .ok()
                .entity(vacancyItemsApiHh)
                .build();
    }

    @GET
    @Path(value = "/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEmployerById(@PathParam(value = "id") Integer id) {

        VacancyDto vacancy;
        try {
            vacancy = apiHhService.getVacancyBy(id);
        } catch (HhRequestException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }

        return Response
                .ok()
                .entity(vacancy)
                .build();
    }
}
