package ru.hh.back.resource;

import ru.hh.back.dto.VacancyRequestDto;
import ru.hh.back.service.NegotiationService;
import ru.hh.back.service.VacancyService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/vacancy")
public class VacancyResource {
    private VacancyService vacancyService;
    private NegotiationService negotiationService;

    public VacancyResource(VacancyService vacancyService, NegotiationService negotiationDao) {
        this.vacancyService = vacancyService;
        this.negotiationService = negotiationDao;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancy() {
        var vacancy = vacancyService.getVacancy();
        return Response.ok(vacancy).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancy(@PathParam("id") Integer id) {
        var vacancy = vacancyService.getVacancy(id);
        if (vacancy == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vacancy).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVacancy(VacancyRequestDto vacancyDto) {
        Integer vacancyId = vacancyService.createVacancy(vacancyDto);
        return Response.ok(vacancyId).build();
    }

    @GET
    @Path("/{id}/negotiations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNegotiation(@PathParam("id") Integer id) {
        var negotiations = negotiationService.getVacancyNegotiation(id);
        return Response.ok(negotiations).build();
    }
}
