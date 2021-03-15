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

import ru.hh.school.service.EmployerService;
import ru.hh.school.view.EmployerExtendedView;
import ru.hh.school.view.EmployerShortView;

@Path("/employer")
@Produces(MediaType.APPLICATION_JSON)
public class EmployerResource {

  private final EmployerService employerService;

  @Inject
  private EmployerResource(EmployerService eService) {
    employerService = eService;
  }

  @GET
  public Response getEmployers(
      @QueryParam("query") String query,
      @QueryParam("page") Integer page,
      @QueryParam("per_page") Integer perPage)
      throws JsonProcessingException, IOException {
    return Response.ok(employerService.getEmployers(query, page, perPage).stream()
      .map(EmployerShortView::new).collect(toList())).build();
  }

  @GET
  @Path("/{employer_id}")
  public Response getEmployer(@PathParam("employer_id") Integer employerId) throws JsonProcessingException {
    return Response.ok(new EmployerExtendedView(employerService.getEmployer(employerId))).build();
  }
}
