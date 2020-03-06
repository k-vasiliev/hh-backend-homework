package ru.hh.school.resource;

import ru.hh.school.dto.request.CompanyRequestDto;
import ru.hh.school.dto.response.CompanyResponseDto;
import ru.hh.school.service.CompanyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/company")
public class CompanyResource {

    private CompanyService companyService;

    @Inject
    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompanyResponseDto> getAllCompanies() {
        return companyService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(CompanyRequestDto companyDto) {
        companyService.saveNew(companyDto);
    }
}

