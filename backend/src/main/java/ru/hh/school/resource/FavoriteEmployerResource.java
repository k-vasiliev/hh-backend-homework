package ru.hh.school.resource;

import static java.util.stream.Collectors.toList;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.dao.DataIntegrityViolationException;

import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.service.FavoriteEmployerService;
import ru.hh.school.view.FavoriteEmployerView;
import ru.hh.school.view.Popularity;

@Path("/favorites/employer")
@Produces(MediaType.APPLICATION_JSON)
public class FavoriteEmployerResource {

  public static final int COMMENT_MAX_LENGTH = 100;

  private static final String COMMENT_TOO_LONG_MESSAGE = "Comment should be less than " + COMMENT_MAX_LENGTH + " symbols";

  private final FavoriteEmployerService service;

  private final FileSettings settings;

  @Inject
  private FavoriteEmployerResource(FavoriteEmployerService service, FileSettings settings) {
    this.service = service;
    this.settings = settings;
  }

  @POST
  public Response addToFavorites(
      @FormParam("employer_id") Integer employerId,
      @FormParam("comment") String comment)
      throws JsonProcessingException {
    if (!valid(comment))
      return Response.status(Status.BAD_REQUEST.getStatusCode(), COMMENT_TOO_LONG_MESSAGE).build();
    try {
      service.addToFavorites(employerId, comment);
      return Response.ok().build();
    }
    catch (DataIntegrityViolationException e) {
      return Response.status(Status.BAD_REQUEST.getStatusCode(), "Employer already in favorites").build();
    }
  }

  @GET
  public Response getFavoriteEmployers(@QueryParam("page") Integer page, @QueryParam("per_page") Integer perPage) {
    page = Optional.ofNullable(page).orElse(settings.getInteger("pagination.default_page"));
    perPage = Optional.ofNullable(perPage).orElse(settings.getInteger("pagination.default_per_page"));
    if (page < 0 || perPage < 0)
      return Response.status(Status.BAD_REQUEST.getStatusCode(), "Pagination arguments can't be negative").build();
    return Response.ok(service.getFavoriteEmployers(page, perPage).stream().map(FavoriteEmployerView::new)
        .peek(view -> view.setPopularity(Popularity.calculate(view.getViewsCount(),
          settings.getInteger("favorite_companies.popularity_limit"))))
        .collect(toList())).build();
  }

  @PUT
  @Path("/{employer_id}")
  public Response updateEmployer(
      @PathParam("employer_id") Integer employerId,
      @FormParam("comment") String comment) {
    if (!valid(comment))
      return Response.status(Status.BAD_REQUEST.getStatusCode(), COMMENT_TOO_LONG_MESSAGE).build();
    service.updateEmployer(employerId, comment);
    return Response.ok().build();
  }

  @DELETE
  @Path("/{employer_id}")
  public Response deleteFromFavorites(@PathParam("employer_id") Integer employerId) {
    service.removeFromFavorites(employerId);
    return Response.ok().build();
  }

  @POST
  @Path("/{employer_id}/refresh")
  public Response refreshEmployerData(@PathParam("employer_id") Integer employerId) throws JsonProcessingException {
    service.refreshEmployerData(employerId);
    return Response.ok().build();
  }

  static private boolean valid(String comment) {
    return comment == null || comment.length() <= COMMENT_MAX_LENGTH;
  }
}
