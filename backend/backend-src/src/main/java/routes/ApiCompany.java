package routes;

import com.fasterxml.jackson.annotation.JsonProperty;
import dto.CompanyDto;
import dto.NewCompanyInfo;
import dto.UserDto;
import service.CompanyService;
import service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/company")
public class ApiCompany {
    private CompanyService  companyService;

    @Inject
    public ApiCompany(CompanyService companyService) {
        this.companyService = companyService;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newCompany(NewCompanyInfo info) {
        companyService.addCompany(info.getName(), info.getUserId());
        return  Response.ok().build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanies() {

        List<CompanyDto> users = companyService.getCompanies()
                .stream()
                .map(CompanyDto::new)
                .collect(Collectors.toList());

        return Response.ok(users).build();

    }


}
