package ru.hh.back.resource;

import ru.hh.back.dao.CompanyDao;
import ru.hh.back.dto.CompanyDto;
import ru.hh.back.service.Mapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/api/company")
public class CompanyResource {

    private CompanyDao companyDao;
    public CompanyResource(CompanyDao companyDao){
        this.companyDao =  companyDao;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompany() {
        var companies = companyDao.getCompany();
        var usersDto = companies.stream().map(Mapper::map).collect(Collectors.toList());
        return Response.ok(usersDto).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompany(CompanyDto company) {
        Integer userId = companyDao.save(Mapper.map(company));
        return Response.ok(userId).build();
    }
}


