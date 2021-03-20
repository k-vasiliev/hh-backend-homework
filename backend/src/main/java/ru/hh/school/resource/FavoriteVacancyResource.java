package ru.hh.school.resource;

import static java.util.stream.Collectors.toList;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import ru.hh.school.service.FavoriteVacancyService;
import ru.hh.school.view.FavoriteVacancyView;
import ru.hh.school.view.Popularity;

@Path("/favorites/vacancy")
@Produces(MediaType.APPLICATION_JSON)
public class FavoriteVacancyResource {

  public static final int COMMENT_MAX_LENGTH = 100;

  private static final String COMMENT_TOO_LONG_MESSAGE = "Comment should be less than " + COMMENT_MAX_LENGTH + " symbols";

  private final FavoriteVacancyService service;

  private final FileSettings settings;

  @Inject
  private FavoriteVacancyResource(FavoriteVacancyService service, FileSettings settings) {
    this.service = service;
    this.settings = settings;
  }

  @POST
  public Response addToFavorites(
      @FormParam("vacancy_id") Integer vacancyId,
      @FormParam("comment") String comment)
      throws JsonProcessingException {
    if (!valid(comment))
      return Response.status(Status.BAD_REQUEST.getStatusCode(), COMMENT_TOO_LONG_MESSAGE).build();
    try {
      service.addToFavorites(vacancyId, comment);
      return Response.ok().build();
    }
    catch (DataIntegrityViolationException e) {
      return Response.status(Status.BAD_REQUEST.getStatusCode(), "Vacancy already in favorites").build();
    }
  }

  @GET
  public Response getFavoriteVacancies(@QueryParam("page") Integer page, @QueryParam("per_page") Integer perPage) {
    page = Optional.ofNullable(page).orElse(settings.getInteger("pagination.default_page"));
    perPage = Optional.ofNullable(perPage).orElse(settings.getInteger("pagination.default_per_page"));
    if (page < 0 || perPage < 0)
      return Response.status(Status.BAD_REQUEST.getStatusCode(), "Pagination arguments can't be negative").build();
    return Response.ok(service.getFavoriteVacancies(page, perPage).stream().map(FavoriteVacancyView::new)
        .peek(view -> view.setPopularity(Popularity.calculate(view.getViewsCount(),
          settings.getInteger("favorite_vacancies.popularity_limit"))))
        .collect(toList())).build();
  }

  @DELETE
  @Path("/{vacancy_id}")
  public Response deleteFromFavorites(@PathParam("vacancy_id") Integer vacancyId) {
    service.removeFromFavorites(vacancyId);
    return Response.ok().build();
  }

  @POST
  @Path("/{vacancy_id}/refresh")
  public Response refreshVacancyData(@PathParam("vacancy_id") Integer vacancyId) throws JsonProcessingException {
    service.refreshVacancyData(vacancyId);
    return Response.ok().build();
  }

  static private boolean valid(String comment) {
    return comment == null || comment.length() <= COMMENT_MAX_LENGTH;
  }
}
