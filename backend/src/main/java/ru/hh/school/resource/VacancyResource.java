package ru.hh.school.resource;

import org.springframework.validation.annotation.Validated;
import ru.hh.school.dto.EmployerDetailed;
import ru.hh.school.dto.Vacancies;
import ru.hh.school.dto.Vacancy;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/vacancy")
@Validated
public class VacancyResource {

    private final VacancyService vacancyService;

    public VacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancies(@QueryParam(value = "page") @Min(0) Integer page,
                                 @QueryParam(value = "per_page") @Min(0) @Max(100) Integer perPage,
                                 @QueryParam(value = "query") @NotBlank String query) {
        Vacancies vacancies = vacancyService.findVacancies(page, perPage, query);

        return Response.ok(vacancies).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployer(@PathParam("id") @NotNull Integer id) {
        Vacancy vacancy = vacancyService.get(id);

        return Response.ok(vacancy).build();
    }

}
