package ru.hh.school.resource;

import ru.hh.school.dto.VacancyItemsApi;
import ru.hh.school.dto.response.ErrorResponseDto;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vacancy")
@Singleton
public class VacancyResource {

    private final VacancyService vacancyService;

    public VacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancies(@QueryParam("page") Integer page,
                                 @QueryParam("per_page") Integer perPage,
                                 @QueryParam("query") String query) {

        VacancyItemsApi vacancyItemsApi = null;
        try {
            vacancyItemsApi = vacancyService.getVacancy(page, perPage, query);
        } catch (HhRequestException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }

        return Response
                .ok()
                .entity(vacancyItemsApi)
                .build();
    }
}
