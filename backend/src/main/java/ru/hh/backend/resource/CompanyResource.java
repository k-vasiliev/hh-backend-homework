package ru.hh.backend.resource;

import javassist.NotFoundException;
import ru.hh.backend.dto.CompanyDtoRequest;
import ru.hh.backend.mapper.CompanyMapper;
import ru.hh.backend.service.CompanyService;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class CompanyResource {

    private CompanyService companyService;
    private CompanyMapper companyMapper;

    public CompanyResource(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GET
    public Response getCompanies() {
        return Response.ok(
                companyService.getCompanies().stream().map(companyMapper::map).collect(Collectors.toList())
        ).build();
    }

    @POST
    public Response createCompany(CompanyDtoRequest companyDtoRequest) {
        try {
            return Response.ok(
                    companyService.save(companyMapper.map(companyDtoRequest))
            ).build();
        } catch (NotFoundException e) {
            return Response.status(404, e.getMessage()).build();
        }
    }
}
