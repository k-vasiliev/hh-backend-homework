package ru.hh.school.resource;

import static java.util.stream.Collectors.toList;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import ru.hh.school.service.VacancyService;
import ru.hh.school.view.VacancyExtendedView;
import ru.hh.school.view.VacancyShortView;

@Path("/vacancy")
@Produces(MediaType.APPLICATION_JSON)
public class VacancyResource {

  private final VacancyService vacancyService;

  @Inject
  private VacancyResource(VacancyService vService) {
    vacancyService = vService;
  }

  @GET
  public Response getVacancies(
      @QueryParam("query") String query,
      @QueryParam("page") Integer page,
      @QueryParam("per_page") Integer perPage)
      throws JsonProcessingException, IOException {
    return Response.ok(vacancyService.getVacancies(query, page, perPage).stream()
      .map(VacancyShortView::new).collect(toList())).build();
  }

  @GET
  @Path("/{vacancy_id}")
  public Response getVacancy(@PathParam("vacancy_id") Integer vacancyId) throws JsonProcessingException {
    return Response.ok(new VacancyExtendedView(vacancyService.getVacancy(vacancyId))).build();
  }
}
