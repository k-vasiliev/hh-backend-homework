package ru.hh.homework.at_least_some_backend.resource;

import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertResumeDto;
import ru.hh.homework.at_least_some_backend.dto.query.HHQueryResumeDto;
import ru.hh.homework.at_least_some_backend.service.HHResumeService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Path("/resume")
public class HHResumeResource
{
    @Inject
    private HHResumeService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HHQueryResumeDto> getAllResumes()
    {
        return service.queryAll()
                .stream()
                .map(HHQueryResumeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertNewResume(HHInsertResumeDto dto)
    {
        service.saveEntity(
                service.createEntity(dto)
        );
    }
}
