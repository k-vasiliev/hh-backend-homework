package ru.hh.school.controller.vacancy;

import ru.hh.school.dto.vacancy.VacancyDtoResponse;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.mapper.VacancyMapper;
import ru.hh.school.service.CompanyService;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/favorites/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class FavVacancyController {

    private final String VACANCY_URL = "https://api.hh.ru/vacancies";
    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;

    private final CompanyService companyService;
    private final Client client;

    public FavVacancyController(VacancyService vacancyService, VacancyMapper vacancyMapper, CompanyService companyService, Client client) {
        this.vacancyService = vacancyService;
        this.vacancyMapper = vacancyMapper;
        this.companyService = companyService;
        this.client = client;
    }

    @POST
    public Response addVacancyToFav(@FormParam("vacancy_id") String vacancyId,
                                    @FormParam("comment") @DefaultValue("") String comment) {
        try {
            Response response = client.target(VACANCY_URL + "/" + vacancyId).request().get();
            int status = response.getStatus();
            VacancyDtoResponse entity = response.readEntity(VacancyDtoResponse.class);
            return Response.status(status).entity(
                    vacancyService.save(vacancyMapper.mapWithSideEffects(entity, comment))
            ).build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{vacancy_id}")
    public Response deleteVacancy(@PathParam("vacancy_id") Integer vacancyId) {
        try {
            vacancyService.deleteVacancy(vacancyId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }


    @POST
    @Path("/{vacancy_id}/refresh")
    public Response refreshVacancy(@PathParam("vacancy_id") Integer vacancyId) {
        try {
            Response response = client.target(VACANCY_URL + "/" + vacancyId).request().get();
            int status = response.getStatus();
            VacancyDtoResponse entity = response.readEntity(VacancyDtoResponse.class);
            Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
            vacancyService.updateVacancy(vacancyMapper.convertWithSideEffects(entity, vacancy));
            return Response.status(status).build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }


    @GET
    public Response getVacancies(@QueryParam("page") @DefaultValue("0") String page,
                                 @QueryParam("per_page") @DefaultValue("10") String perPage) {
        try {
            int pageI = Integer.parseInt(page);
            int perPageI = Integer.parseInt(perPage);
            List<Vacancy> vacancies = vacancyService.getVacancies();
            if (pageI * perPageI + perPageI > vacancies.size()) {
                vacancies = vacancies.subList(pageI * perPageI, pageI * perPageI + perPageI);
            }
            vacancies.forEach(v -> {
                companyService.incrementView(v.getEmployer().getId());
                vacancyService.incrementView(v.getId());
            });
            return Response.ok(
                    vacancies.stream().map(vacancyMapper::mapWithSideEffects)
            ).build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }
}
