package ru.hh.school.resource;

import ru.hh.school.dto.request.CreateCompanyDto;
import ru.hh.school.dto.response.CompanyDto;
import ru.hh.school.service.CompanyService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static ru.hh.school.Utils.check;

@Path("/company")
public class CompanyResource {

    private CompanyService companyService;

    @Inject
    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCompany(CreateCompanyDto dto) {
        check(dto.getName() != null);
        check(!dto.getName().isEmpty());
        check(dto.getName().length()<64);
        companyService.createCompany(dto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompanyDto> getCompanies() {
        return companyService.getCompanies();
    }
}
