package ru.hh.school.resource;

import ru.hh.school.dto.request.CreateVacancyDto;
import ru.hh.school.dto.response.NegotiationDto;
import ru.hh.school.dto.response.ShortVacancyDto;
import ru.hh.school.dto.response.VacancyDto;
import ru.hh.school.service.NegotiationService;
import ru.hh.school.service.VacancyService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static ru.hh.school.Utils.check;


@Path("/vacancy")
public class VacancyResource {

    private VacancyService vacancyService;
    private NegotiationService negotiationService;

    @Inject
    public VacancyResource(VacancyService vacancyService, NegotiationService negotiationService) {
        this.vacancyService = vacancyService;
        this.negotiationService = negotiationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createVacancy(CreateVacancyDto dto) {
        check(dto.getTitle() != null);
        check(!dto.getTitle().isEmpty());
        check(dto.getDescription() != null);
        check(!dto.getDescription().isEmpty());
        check(dto.getContacts() != null);
        check(!dto.getContacts().isEmpty());
        vacancyService.createVacancy(dto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ShortVacancyDto> getALLVacancies() {
        return vacancyService.getAllVacancies();
    }

    @GET
    @Path("/{vacancyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public VacancyDto getVacancy(@PathParam("vacancyId") Integer vacancyId) {
        return vacancyService.getVacancy(vacancyId);
    }

    @GET
    @Path("/{vacancyId}/negotiations")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NegotiationDto> getNegotiationsByVacancyId(@PathParam("vacancyId") Integer vacancyId) {
        return negotiationService.getNegotiationsByVacancyId(vacancyId);
    }
}
