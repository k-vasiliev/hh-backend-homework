package ru.hh.school.controller.employer;

import ru.hh.school.dto.company.CompanyDtoByIdResponse;
import ru.hh.school.entity.Company;
import ru.hh.school.mapper.CompanyMapper;
import ru.hh.school.service.CompanyService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/favorites/employer")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class FavEmployerController {

    private final String EMPLOYER_URL = "https://api.hh.ru/employers";
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final Client client;

    public FavEmployerController(CompanyService companyService, CompanyMapper companyMapper, Client client) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.client = client;
    }

    @POST
    public Response addCompanyToFav(@FormParam("employer_id") String employerId,
                                    @FormParam("comment") @DefaultValue("") String comment) {
        try {
            Response response = client.target(EMPLOYER_URL + "/" + employerId).request().get();
            int status = response.getStatus();
            CompanyDtoByIdResponse entity = response.readEntity(CompanyDtoByIdResponse.class);

            return Response.status(status).entity(
                    companyService.save(companyMapper.mapWithSideEffects(entity, comment))
            ).build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }

    @GET
    public Response getCompanies(@QueryParam("page") @DefaultValue("0") String page,
                                 @QueryParam("per_page") @DefaultValue("10") String perPage) {
        try {
            int pageI = Integer.parseInt(page);
            int perPageI = Integer.parseInt(perPage);
            List<Company> companies = companyService.getCompanies();
            if (pageI * perPageI + perPageI > companies.size()){
                companies = companies.subList(pageI * perPageI, pageI * perPageI + perPageI);
            }
            companies.forEach(c -> companyService.incrementView(c.getId()));
            return Response.ok(
                    companies.stream().map(companyMapper::mapWithSideEffects)
            ).build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{employer_id}")
    public Response updateCompany(@PathParam("employer_id") Integer employerId,
                                  @FormParam("comment") @DefaultValue("") String comment) {
        try {
            companyService.updateComment(employerId, comment);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{employer_id}")
    public Response deleteCompany(@PathParam("employer_id") Integer employerId) {
        try {
            companyService.deleteCompany(employerId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }

    @POST
    @Path("/{employer_id}/refresh")
    public Response refreshCompany(@PathParam("employer_id") Integer employerId) {
        try {
            Response response = client.target(EMPLOYER_URL + "/" + employerId).request().get();
            int status = response.getStatus();
            CompanyDtoByIdResponse entity = response.readEntity(CompanyDtoByIdResponse.class);
            Company company = companyService.getCompanyById(employerId);
            companyService.updateCompany(companyMapper.convertWithSideEffects(entity, company));
            return Response.status(status).build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }
}
