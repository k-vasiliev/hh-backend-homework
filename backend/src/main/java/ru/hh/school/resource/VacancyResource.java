package ru.hh.school.resource;


import ru.hh.school.dto.NegotiationResponseDto;
import ru.hh.school.dto.VacancyDetailsResponseDto;
import ru.hh.school.dto.VacancyRequestDto;
import ru.hh.school.dto.VacancyResponseDto;
import ru.hh.school.mapper.NegotiationMapper;
import ru.hh.school.mapper.VacancyMapper;
import ru.hh.school.service.NegotiationService;
import ru.hh.school.service.VacancyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class VacancyResource {

    private final VacancyService vacancyService;
    private final NegotiationService negotiationService;
    private final VacancyMapper vacancyMapper;
    private final NegotiationMapper negotiationMapper;


    public VacancyResource(VacancyService vacancyService, NegotiationService negotiationService, VacancyMapper vacancyMapper, NegotiationMapper negotiationMapper) {
        this.vacancyService = vacancyService;
        this.negotiationService = negotiationService;
        this.vacancyMapper = vacancyMapper;
        this.negotiationMapper = negotiationMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public VacancyResponseDto create(VacancyRequestDto vacancyRequestDto) {
        return vacancyMapper.map(vacancyService.create(vacancyMapper.map(vacancyRequestDto)));
    }

    @GET
    public List<VacancyResponseDto> getAll() {
        return vacancyService.getAll().stream().map(vacancyMapper::map).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public VacancyDetailsResponseDto getVacancy(@PathParam("id") Long id) {
        return vacancyMapper.mapDetails(vacancyService.get(id));
    }

    @GET
    @Path("/{id}/negotiations")
    public List<NegotiationResponseDto> getNegotiations(@PathParam("id") Long id) {
        return negotiationService.getAllResumeByVacancyId(id).stream().map(negotiationMapper::map).collect(Collectors.toList());
    }
}