package ru.hh.backend.homework.resource;

import ru.hh.backend.homework.dto.NegotiationResponseDto;
import ru.hh.backend.homework.dto.VacancyDetailsResponseDto;
import ru.hh.backend.homework.dto.VacancyRequestDto;
import ru.hh.backend.homework.dto.VacancyResponseDto;
import ru.hh.backend.homework.mapper.NegotiationMapper;
import ru.hh.backend.homework.mapper.VacancyMapper;
import ru.hh.backend.homework.service.NegotiationService;
import ru.hh.backend.homework.service.VacancyService;

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

    @Inject
    public VacancyResource(VacancyService vacancyService, NegotiationService negotiationService, VacancyMapper vacancyMapper, NegotiationMapper negotiationMapper) {
        this.vacancyService = vacancyService;
        this.negotiationService = negotiationService;
        this.vacancyMapper = vacancyMapper;
        this.negotiationMapper = negotiationMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public VacancyResponseDto create(VacancyRequestDto vacancyRequestDto) {
        return vacancyMapper.map(vacancyService.save(vacancyMapper.map(vacancyRequestDto)));
    }

    @GET
    public List<VacancyResponseDto> getAll() {
        return vacancyService.getAll().stream().map(vacancyMapper::map).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public VacancyDetailsResponseDto getVacancy(@PathParam("id") Integer id) {
        return vacancyMapper.mapDetails(vacancyService.get(id).get());
    }

    @GET
    @Path("/{id}/negotiations")
    public List<NegotiationResponseDto> getNegotiations(@PathParam("id") Integer id) {
        return negotiationService.getAllByVacancy(id).stream().map(negotiationMapper::map).collect(Collectors.toList());
    }
}
