package ru.hh.school.resource;

import ru.hh.school.dto.request.CompanyRequestDto;
import ru.hh.school.dto.response.CompanyResponseDto;
import ru.hh.school.service.CompanyService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response getAllCompanies() {
        List<CompanyResponseDto> companiesDto = companyService.getAll();
        return Response.ok(companiesDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCompany(CompanyRequestDto companyDto) {
        companyService.saveNew(companyDto);
        return Response.ok().build();
    }
}

