package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.dto.FavoriteVacancyDto;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favorites/vacancy")
public class FavoritesVacancyResource {

    private static final Logger logger = LoggerFactory.getLogger(FavoritesVacancyResource.class);

    private final VacancyService vacancyService;

    public FavoritesVacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoriteVacancies(
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("20") @QueryParam("per_page") Integer perPage) {
        List<FavoriteVacancyDto> favoriteVacancies = vacancyService.getFavorites(page, perPage);
        System.out.println("LIST: " +  favoriteVacancies);
        return Response.ok().entity(favoriteVacancies).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVacancyToFavorites(
            @FormParam("vacancy_id") Integer vacancyId,
            @DefaultValue("") @FormParam("comment") String comment
    ) {
        try {
            vacancyService.addVacancyToFavorites(vacancyId);
            return Response.ok().build();
        } catch (WebApplicationException exception) {
            throw new WebApplicationException(exception.getMessage(), exception.getResponse().getStatus());
        }
    }

    @DELETE
    @Path("/{vacancy_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCompany(@PathParam("vacancy_id") Integer vacancyId) {
        vacancyService.deleteVacancy(vacancyId);
        return Response.ok().build();
    }

    @POST
    @Path("/{vacancy_id}/refresh")
    @Produces(MediaType.APPLICATION_JSON)
    public Response refresh(@PathParam("vacancy_id") Integer vacancyId) {
        try {
            vacancyService.refresh(vacancyId);
            return Response.ok().build();
        } catch (WebApplicationException exception) {
            throw new WebApplicationException(exception.getMessage(), exception.getResponse().getStatus());
        }
    }

}
