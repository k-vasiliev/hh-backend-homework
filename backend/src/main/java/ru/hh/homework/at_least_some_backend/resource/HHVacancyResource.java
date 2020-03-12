package ru.hh.homework.at_least_some_backend.resource;

import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertVacancyDto;
import ru.hh.homework.at_least_some_backend.dto.query.HHQueryVacancyDto;
import ru.hh.homework.at_least_some_backend.service.HHVacancyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/vacancy")
public class HHVacancyResource
{
    @Inject
    private HHVacancyService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HHQueryVacancyDto> getAllVacancies()
    {
        return service.queryAll()
                .stream()
                .map(HHQueryVacancyDto::fromEntity)
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertNewVacancy(HHInsertVacancyDto dto)
    {
        service.saveEntity(
                service.createEntity(dto)
        );
    }
}
