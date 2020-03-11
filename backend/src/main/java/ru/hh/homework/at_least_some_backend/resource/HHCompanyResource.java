package ru.hh.homework.at_least_some_backend.resource;

import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertCompanyDto;
import ru.hh.homework.at_least_some_backend.dto.query.HHQueryCompanyDto;
import ru.hh.homework.at_least_some_backend.service.HHCompanyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Path("/company")
public class HHCompanyResource
{
    @Inject
    private HHCompanyService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HHQueryCompanyDto> getAllCompanies()
    {
        return service.queryAll()
                .stream()
                .map(HHQueryCompanyDto::fromEntity)
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertNewCompany(HHInsertCompanyDto dto)
    {
        service.insertCompany(
                service.createCompany(dto)
        );
    }
}
