package ru.hh.backend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.CompanyDao;
import ru.hh.backend.dto.request.CompanyRequestDto;
import ru.hh.backend.dto.response.CompanyResponseDto;
import ru.hh.backend.entity.Company;
import ru.hh.backend.service.CompanyMapper;
import ru.hh.backend.service.CompanyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class CompanyController {
    private final CompanyMapper companyMapper;
    private final CompanyService companyService;

    public CompanyController(CompanyMapper companyMapper, CompanyService companyService) {
        this.companyMapper = companyMapper;
        this.companyService = companyService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CompanyRequestDto companyRequestDto) {
        Company company = companyService.create(companyMapper.map(companyRequestDto));
        return Response.ok(companyMapper.map(company)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Company> companies = companyService.getAll();
        return Response.ok(companies.stream().map(companyMapper::map).collect(Collectors.toList())).build();
    }
}
