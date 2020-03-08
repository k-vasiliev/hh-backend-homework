package ru.hh.backend.resource;

import javassist.NotFoundException;
import ru.hh.backend.dto.VacancyDtoRequest;
import ru.hh.backend.entity.Vacancy;
import ru.hh.backend.mapper.NegotiationMapper;
import ru.hh.backend.mapper.VacancyMapper;
import ru.hh.backend.service.NegotiationService;
import ru.hh.backend.service.VacancyService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class VacancyResource {

    private VacancyService vacancyService;
    private VacancyMapper vacancyMapper;
    private NegotiationService negotiationService;
    private NegotiationMapper negotiationMapper;

    public VacancyResource(VacancyService vacancyService, VacancyMapper vacancyMapper, NegotiationService negotiationService, NegotiationMapper negotiationMapper) {
        this.vacancyService = vacancyService;
        this.vacancyMapper = vacancyMapper;
        this.negotiationService = negotiationService;
        this.negotiationMapper = negotiationMapper;
    }

    @GET
    public Response getVacancies() {
        return Response.ok(
                vacancyService.getVacancies().stream().map(vacancyMapper::map).collect(Collectors.toList())
        ).build();
    }

    @GET
    @Path("/{vacancyId}")
    public Response getVacancy(@PathParam("vacancyId") Integer vacancyId) {
        try {
            return Response.ok(
                    vacancyMapper.map(vacancyService.getVacancy(vacancyId))
            ).build();
        } catch (NotFoundException e) {
            return Response.status(404, e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVacancy(@Valid VacancyDtoRequest vacancyDtoRequest) {
        try {
            return Response.ok(
                vacancyService.save(vacancyMapper.map(vacancyDtoRequest))
            ).build();
        } catch (NotFoundException e) {
            return Response.status(404, e.getMessage()).build();
        }
    }

    @GET
    @Path("/{vacancyId}/negotiations")
    public Response getVacancyNegotiations(@PathParam("vacancyId") Integer vacancyId) {
        try {
            Vacancy vacancy = vacancyService.getVacancy(vacancyId);
            return Response.ok(
                   negotiationService.getVacancyNegotiations(vacancy.getVacancyId())
                           .stream().map(negotiationMapper::map).collect(Collectors.toList())
            ).build();
        } catch (NotFoundException e) {
            return Response.status(404, e.getMessage()).build();
        }
    }
}
