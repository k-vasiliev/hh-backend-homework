package ru.hh.back.resource;

import ru.hh.back.dto.CompanyDto;
import ru.hh.back.service.CompanyService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/company")
public class CompanyResource {

    private CompanyService companyService;
    public CompanyResource(CompanyService service){
        this.companyService =  service;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompany() {
        var companies = companyService.getCompany();
        return Response.ok(companies).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompany(CompanyDto company) {
        Integer userId = companyService.saveCompany(company);
        return Response.ok(userId).build();
    }
}


