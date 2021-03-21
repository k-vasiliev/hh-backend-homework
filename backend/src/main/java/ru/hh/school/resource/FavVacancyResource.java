package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Singleton
@Path("/favorites/vacancy")
public class FavVacancyResource {

    private final VacancyService vacancyService;

    private static final Logger logger = LoggerFactory.getLogger(FavVacancyResource.class);

    public FavVacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GET
    @Produces("application/json")
    public Response getVacancies(@QueryParam(value = "page") @DefaultValue("0") Integer page,
                                 @QueryParam(value = "per_page") @DefaultValue("20") Integer per_page) {
        if (per_page < 0 || per_page > 100) return Response
                .status(400, "per_page can't be more than 100, and less than 0").build();
        List<Vacancy> vacancies = vacancyService.getVacancyFromFavorites(page, per_page);

        if(vacancies != null) return Response.ok(vacancies).build();

        return Response.status(400).build();
    }

    @POST
    @Produces("application/json")
    public Response saveVacancy(@QueryParam(value = "vacancy_id") Integer id,
                                @QueryParam(value = "comment") String comment) {
        logger.info("Saving Vacancy to favorites with id: "
                + id + "and comment: " + comment);

        try {
            vacancyService.saveVacancyToFavorites(id, comment);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/{vacancy_id}")
    @Produces("application/json")
    public Response editVacancy(@PathParam(value = "vacancy_id") Integer id,
                                @QueryParam(value = "comment") String comment) {
        logger.info("Editing vacancy with id: "
                + id + " and comment: " + comment);

        Vacancy vacancy = vacancyService.editVacancyFavorites(id, comment);

        if (vacancy != null) return Response.ok(vacancy).build();
        return Response.status(400, "Vacancy with id:" + id + " not found").build();
    }

    @DELETE
    @Path("/{vacancy_id}")
    @Produces("application/json")
    public Response deleteVacancy(@PathParam(value = "vacancy_id") Integer id) {
        logger.info("Deleting vacancy with id: " + id);

        Vacancy vacancy = vacancyService.deleteVacancyFavorites(id);

        if(vacancy != null) return Response.ok(vacancy).build();
        return Response.status(400, "Vacancy with id: " + id + " not found").build();
    }

    @POST
    @Path("/{vacancy_id}/refresh")
    @Produces("application/json")
    public Response refreshVacancy(@PathParam(value = "vacancy_id") Integer id) {
        logger.info("Refreshing employer with id: " + id);

        Vacancy vacancy = null;

        try {
            vacancy = vacancyService.refreshVacancyFavorites(id);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }

        if(vacancy != null) return Response.ok().build();
        return Response.status(400, "Vacancy with id: " + id + "not found").build();
    }

}
