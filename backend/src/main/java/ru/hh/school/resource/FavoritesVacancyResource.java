package ru.hh.school.resource;

import org.springframework.validation.annotation.Validated;
import ru.hh.school.entity.VacancyEntity;
import ru.hh.school.request.FavoritesVacancyRequest;
import ru.hh.school.service.FavoritesVacancyService;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favorites/vacancy")
@Validated
public class FavoritesVacancyResource {

    private final FavoritesVacancyService favoritesVacancyService;

    public FavoritesVacancyResource(FavoritesVacancyService favoritesVacancyService) {
        this.favoritesVacancyService = favoritesVacancyService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployerToFavorites(FavoritesVacancyRequest request) {
        favoritesVacancyService.addVacancy(request);

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoriteVacancies(@QueryParam(value = "page") Integer page,
                                         @QueryParam(value = "per_page") Integer perPage) {
        List<VacancyEntity> vacancies = favoritesVacancyService.getVacancies(page, perPage);

        return Response.ok(vacancies).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFavoriteVacancy(FavoritesVacancyRequest request, @PathParam("id") @NotNull Integer id) {
        request.setVacancyId(id);
        favoritesVacancyService.update(request);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVacancyFromFavorites(@PathParam("id") @NotNull Integer id) {
        favoritesVacancyService.delete(id);

        return Response.ok().build();
    }

    @POST
    @Path("/{id}/refresh")
    @Produces(MediaType.APPLICATION_JSON)
    public Response refreshFavoriteVacancy(@PathParam("id") @NotNull Integer id) {
        favoritesVacancyService.refresh(id);

        return Response.ok().build();
    }

}
