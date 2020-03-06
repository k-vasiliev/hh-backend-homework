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
    public List<VacancyResponseDto> getAllVacancies() {
        return vacancyService.getAll();
    }

    @GET
    @Path("/{vacancyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public VacancyPopupResponseDto getVacancy(@PathParam("vacancyId") Integer vacancyId) {
        return vacancyService.getVacancyById(vacancyId);
    }

    @GET
    @Path("/{vacancyId}/negotiations")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NegotiationResponseDto> getNegotiationsVacancy(@PathParam("vacancyId") Integer vacancyId) {
        return negotiationService.getNegotiationsByVacancyId(vacancyId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(VacancyRequestDto vacancyDto) {
        vacancyService.saveNew(vacancyDto);
    }
}
