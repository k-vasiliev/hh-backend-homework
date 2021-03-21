package ru.hh.school.resource;

import org.springframework.validation.annotation.Validated;
import ru.hh.school.entity.EmployerEntity;
import ru.hh.school.request.FavoriteEmployersRequest;
import ru.hh.school.service.FavoriteEmployersService;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favorites/employer")
@Validated
public class FavoriteEmployersResource {

    private final FavoriteEmployersService favoriteEmployersService;

    public FavoriteEmployersResource(FavoriteEmployersService favoritesEmployerService) {
        this.favoriteEmployersService = favoritesEmployerService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployerToFavorites(FavoriteEmployersRequest request) {
        favoriteEmployersService.addEmployer(request);

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoriteEmployers(@QueryParam(value = "page") Integer page,
        @QueryParam(value = "per_page") Integer perPage) {
        synchronized (this) {
            List<EmployerEntity> employers = favoriteEmployersService.getEmployers(page, perPage);

            return Response.ok(employers).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFavoriteEmployer(FavoriteEmployersRequest request, @PathParam("id") @NotNull Integer id) {
        request.setEmployerId(id);
        favoriteEmployersService.update(request);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployerFromFavorites(@PathParam("id") @NotNull Integer id) {
        favoriteEmployersService.delete(id);

        return Response.ok().build();
    }

    @POST
    @Path("/{id}/refresh")
    @Produces(MediaType.APPLICATION_JSON)
    public Response refreshFavoriteEmployer(@PathParam("id") @NotNull Integer id) {
        favoriteEmployersService.refresh(id);

        return Response.ok().build();
    }

}