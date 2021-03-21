package ru.hh.school.resource;

import org.springframework.validation.annotation.Validated;
import ru.hh.school.entity.VacancyEntity;
import ru.hh.school.request.FavoriteVacanciesRequest;
import ru.hh.school.service.FavoriteVacanciesService;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favorites/vacancy")
@Validated
public class FavoriteVacanciesResource {

    private final FavoriteVacanciesService favoriteVacanciesService;

    public FavoriteVacanciesResource(FavoriteVacanciesService favoriteVacanciesService) {
        this.favoriteVacanciesService = favoriteVacanciesService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployerToFavorites(FavoriteVacanciesRequest request) {
        favoriteVacanciesService.addVacancy(request);

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoriteVacancies(@QueryParam(value = "page") Integer page,
        @QueryParam(value = "per_page") Integer perPage) {
        synchronized (this) {
            List<VacancyEntity> vacancies = favoriteVacanciesService.getVacancies(page, perPage);

            return Response.ok(vacancies).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFavoriteVacancy(FavoriteVacanciesRequest request, @PathParam("id") @NotNull Integer id) {
        request.setVacancyId(id);
        favoriteVacanciesService.update(request);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVacancyFromFavorites(@PathParam("id") @NotNull Integer id) {
        favoriteVacanciesService.delete(id);

        return Response.ok().build();
    }

    @POST
    @Path("/{id}/refresh")
    @Produces(MediaType.APPLICATION_JSON)
    public Response refreshFavoriteVacancy(@PathParam("id") @NotNull Integer id) {
        favoriteVacanciesService.refresh(id);

        return Response.ok().build();
    }

}