package ru.hh.homework.at_least_some_backend.resource;

import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertNegotiationDto;
import ru.hh.homework.at_least_some_backend.dto.query.HHQueryNegotiationDto;
import ru.hh.homework.at_least_some_backend.service.HHNegotiationService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Path("/negotiation")
public class HHNegotiationResource
{
    @Inject
    private HHNegotiationService service;

    @GET
    @Path("forVacancy/{vacancy_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HHQueryNegotiationDto> getNegotiationsForVacancy(@PathParam("vacancy_id") Long vacancyId)
    {
        return service.queryAllByVacancyId(vacancyId)
                .stream()
                .map(HHQueryNegotiationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertNewNegotiation(HHInsertNegotiationDto dto)
    {
        service.saveEntity(
                service.createEntity(dto)
        );
    }
}
