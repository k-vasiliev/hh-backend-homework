package controller;

import dto.NewVacancyDto;
import dto.VacancyDto;
import dto.VacancyResponseDto;
import service.VacancyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/vacancy")
public class ApiVacancy {
    private final VacancyService vacancyService;

    @Inject
    public ApiVacancy(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }


    @GET
    @Path(value = "/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vacancyAll() {
        List<VacancyDto> vacancies = vacancyService.getVacancies()
                .stream()
                .map(VacancyDto::new)
                .collect(Collectors.toList());

        return Response.ok(vacancies).build();
    }

    @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vacancyById(@PathParam("id") Integer id) {
        VacancyDto vacancy = new VacancyDto(vacancyService.getVacancyById(id));
        return Response.ok(vacancy).build();
    }

    @GET
    @Path(value = "/{id}/negotiations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vacancyNegotiations(@PathParam("id") Integer id) {
        List<VacancyResponseDto> vacancyResponses = vacancyService.getVacancyResponses(id)
                .stream()
                .map(VacancyResponseDto::new)
                .collect(Collectors.toList());

        return Response.ok(vacancyResponses).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVacancy(NewVacancyDto newVacancy) {
        Response.ResponseBuilder response;

        try {
            Integer vacancyId = vacancyService.addVacancy(newVacancy);
            response =  Response.ok(vacancyId);
        } catch (Exception E) {
            response =  Response.status(Response.Status.CONFLICT);
        }

        return response.build();
    }

}
