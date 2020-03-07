package ru.hh.school.resource;

import ru.hh.school.dto.response.NegotiationResponseDto;
import ru.hh.school.dto.response.VacancyPopupResponseDto;
import ru.hh.school.dto.request.VacancyRequestDto;
import ru.hh.school.dto.response.VacancyResponseDto;
import ru.hh.school.service.NegotiationService;
import ru.hh.school.service.VacancyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/vacancy")
public class VacancyResource {

    private VacancyService vacancyService;
    private NegotiationService negotiationService;

    @Inject
    public VacancyResource(VacancyService vacancyService, NegotiationService negotiationService) {
        this.vacancyService = vacancyService;
        this.negotiationService = negotiationService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVacancies() {
        List<VacancyResponseDto> vacanciesDto = vacancyService.getAll();
        return Response.ok(vacanciesDto).build();
    }

    @GET
    @Path("/{vacancyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancy(@PathParam("vacancyId") Integer vacancyId) {
        VacancyPopupResponseDto vacancyPopupDto = vacancyService.getVacancyDtoById(vacancyId);
        return Response.ok(vacancyPopupDto).build();
    }

    @GET
    @Path("/{vacancyId}/negotiations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNegotiationsVacancy(@PathParam("vacancyId") Integer vacancyId) {
        List<NegotiationResponseDto> negotiationsDto = negotiationService.getNegotiationsDtoByVacancyId(vacancyId);
        return Response.ok(negotiationsDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVacancy(VacancyRequestDto vacancyDto) {
        vacancyService.saveNew(vacancyDto);
        return Response.ok().build();
    }
}
