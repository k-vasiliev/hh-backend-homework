package ru.hh.backend.homework.resource;

import ru.hh.backend.homework.dto.CompanyRequestDto;
import ru.hh.backend.homework.dto.CompanyResponseDto;
import ru.hh.backend.homework.mapper.CompanyMapper;
import ru.hh.backend.homework.service.CompanyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/company")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class CompanyResource {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Inject
    public CompanyResource(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public CompanyResponseDto create(CompanyRequestDto companyRequestDto) {
        return companyMapper.map(companyService.save(companyMapper.map(companyRequestDto)));
    }

    @GET
    public List<CompanyResponseDto> getAll() {
        return companyService.getAll().stream().map(companyMapper::map).collect(Collectors.toList());
    }
}
