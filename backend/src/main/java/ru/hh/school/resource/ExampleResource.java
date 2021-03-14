package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.hh.school.dao.FavouritesDao;
import ru.hh.school.hhapiclient.HHClient;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.IOException;
import java.lang.InterruptedException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ExampleResource {

  private FavouritesDao dao;
  private HHClient hhClient;

  //@Inject
  public ExampleResource(FavouritesDao dao, HHClient client) {
    this.dao = dao;
    this.hhClient = client;
  }

  private static final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

  @GET
  public void dummy() {
    logger.info("Do nothing");
  }

  @Path("/favorites/employer")
  @POST
  public Response createFavouriteEmployer(@FormParam("employer_id") Integer id,
                                      @DefaultValue("") @FormParam("comment") String comment) throws RuntimeException {
    try {
      ru.hh.school.hhapiclient.Employer hhEmployer = hhClient.getEmployer(Integer.toString(id));
      ru.hh.school.entity.FavouriteEmployer newFavEmployer = dao.employerFromHHEmployer(hhEmployer);
      // update the fields
      newFavEmployer.setComment(comment);
      // save to db
      ru.hh.school.entity.FavouriteEmployer saved = dao.create_employer(newFavEmployer);
      return Response.status(200).entity(ru.hh.school.dto.FavouriteEmployer.fromEntity(saved)).build();
    } catch (IOException | InterruptedException e) {
      logger.warn("got exception " + e);
      return Response.status(500).build();
    }
  }

  @Path("/favorites/employer")
  @GET
  public Response getFavouriteEmployers(@DefaultValue("0") @QueryParam("page") int page, @DefaultValue("20") @QueryParam("per_page") int per_page) {

    List<ru.hh.school.entity.FavouriteEmployer> favEmployers = dao.list_employers(page, per_page);

    // increment views
    favEmployers.stream().forEach(favEmployer -> dao.incrementEmployerViews(favEmployer.getId()));
    return Response.status(200).entity(
            favEmployers.stream()
                    .map(favEmployer -> ru.hh.school.dto.FavouriteEmployer.fromEntity(favEmployer)).collect(Collectors.toList())
    ).build();
  }

  @Path("/favorites/employer/{employer_id}")
  @PUT
  public Response editFavouriteEmployer(@PathParam("employer_id") Integer id,
                                    @DefaultValue("") @FormParam("comment") String comment) {
    ru.hh.school.entity.FavouriteEmployer favEmployer = dao.get_employer(id);
    favEmployer.setComment(comment);
    ru.hh.school.entity.FavouriteEmployer saved = dao.update_employer(favEmployer);
    return Response.status(200).entity(ru.hh.school.dto.FavouriteEmployer.fromEntity(saved)).build();
  }

  @Path("/favorites/employer/{employer_id}")
  @DELETE
  public Response deleteFavouriteEmployer(@PathParam("employer_id") Integer id) {
    dao.remove_employer(id);
    return Response.status(204).build();
  }

  @Path("/favorites/employer/{employer_id}/refresh")
  @POST
  public Response refreshFavouriteEmployer(@PathParam("employer_id") Integer id) {

    try {
      ru.hh.school.entity.FavouriteEmployer favEmployer = dao.get_employer(id);
      ru.hh.school.hhapiclient.Employer hhEmployer = hhClient.getEmployer(Integer.toString(favEmployer.getHHId()));
      ru.hh.school.entity.FavouriteEmployer newFavEmployer = dao.employerFromHHEmployer(hhEmployer);
      // update the fields
      favEmployer.setName(newFavEmployer.getName());
      favEmployer.setDescription(newFavEmployer.getDescription());
      ru.hh.school.entity.Area newArea = favEmployer.getArea();
      newArea.setName(newFavEmployer.getArea().getName());
      favEmployer.setArea(newArea);
      // save to db
      ru.hh.school.entity.FavouriteEmployer saved = dao.update_employer(favEmployer);
      return Response.status(200).entity(ru.hh.school.dto.FavouriteEmployer.fromEntity(saved)).build();
    } catch (Exception e) {
      logger.warn("got exception " + e);
      return Response.status(500).build();
    }
  }

  @Path("/favorites/vacancy")
  @POST
  public Response createFavouriteVacancy(@FormParam("vacancy_id") Integer id,
                                     @DefaultValue("") @FormParam("comment") String comment) throws RuntimeException {
    try {
      ru.hh.school.hhapiclient.Vacancy hhVacancy = hhClient.getVacancy(Integer.toString(id));
      ru.hh.school.entity.FavouriteVacancy newFavVacancy = dao.vacancyFromHHVacancy(hhVacancy);
      // update the fields
      newFavVacancy.setComment(comment);
      // save to db
      ru.hh.school.entity.FavouriteVacancy saved = dao.create_vacancy(newFavVacancy);

      // also save employer
      ru.hh.school.hhapiclient.Employer hhEmployer = hhClient.getEmployer(Integer.toString(saved.getEmployerId()));
      ru.hh.school.entity.FavouriteEmployer newFavEmployer = dao.employerFromHHEmployer(hhEmployer);
      // update the fields
      newFavEmployer.setComment("");
      // save to db
      dao.create_employer(newFavEmployer);
      return Response.status(200).entity(ru.hh.school.dto.FavouriteVacancy.fromEntity(saved)).build();
    } catch (IOException | InterruptedException e) {
      logger.warn("got exception " + e);
      return Response.status(500).build();
    }
  }

  @Path("/favorites/vacancy")
  @GET
  public Response getFavouriteVacancies(@DefaultValue("0") @QueryParam("page") int page, @DefaultValue("20") @QueryParam("per_page") int per_page) {

    List<ru.hh.school.entity.FavouriteVacancy> favVacancies = dao.list_vacancies(page, per_page);

    // increment views
    favVacancies.stream().forEach(favVacancy -> {dao.incrementVacancyViews(favVacancy.getId());
                                                 dao.incrementEmployerViews(favVacancy.getEmployerId());});
    return Response.status(200).entity(
            favVacancies.stream()
                    .map(favVacancy -> ru.hh.school.dto.FavouriteVacancy.fromEntity(favVacancy)).collect(Collectors.toList())
    ).build();
  }

  @Path("/favorites/vacancy/{vacancy_id}")
  @DELETE
  public Response deleteFavouriteVacancy(@PathParam("vacancy_id") Integer id) {
    dao.remove_vacancy(id);
    return Response.status(204).build();
  }

  @Path("/favorites/vacancy/{vacancy_id}/refresh")
  @POST
  public Response refreshFavouriteVacancy(@PathParam("vacancy_id") Integer id) {
    try {
      ru.hh.school.entity.FavouriteVacancy favVacancy = dao.get_vacancy(id);
      ru.hh.school.hhapiclient.Vacancy hhVacancy = hhClient.getVacancy(Integer.toString(favVacancy.getHHId()));
      ru.hh.school.entity.FavouriteVacancy newFavVacancy = dao.vacancyFromHHVacancy(hhVacancy);
      // update the fields
      favVacancy.setEmployerId(newFavVacancy.getEmployerId());
      ru.hh.school.entity.Area newArea = favVacancy.getArea();
      newArea.setName(newFavVacancy.getArea().getName());
      favVacancy.setArea(newArea);
      favVacancy.setName(newFavVacancy.getName());
      favVacancy.setCompensationTo(newFavVacancy.getCompensationTo());
      favVacancy.setCompensationFrom(newFavVacancy.getCompensationFrom());
      favVacancy.setCompensationGross(newFavVacancy.getCompensationGross());
      // save to db
      ru.hh.school.entity.FavouriteVacancy saved = dao.update_vacancy(favVacancy);
      return Response.status(200).entity(ru.hh.school.dto.FavouriteVacancy.fromEntity(saved)).build();
    } catch (Exception e) {
      logger.warn("got exception " + e);
      return Response.status(500).build();
    }

  }

  // proxy methods
  @Path("/vacancy")
  @GET
  public Response getVacancies(@QueryParam("query") String query, @DefaultValue("0") @QueryParam("page") int page, @DefaultValue("20") @QueryParam("per_page") int per_page) throws RuntimeException {
    try {
      List<ru.hh.school.hhapiclient.Vacancy> hhVacancies = hhClient.getVacancies(query, page, per_page);
      return Response.status(200).entity(
              hhVacancies.stream()
                      .map(hhVacancy -> ru.hh.school.dto.Vacancy.fromHHVacancy(hhVacancy)).collect(Collectors.toList())
      ).build();
    } catch (IOException | InterruptedException e) {
      logger.warn("got exception " + e);
      return Response.status(500).build();
    }
  }

  @Path("/vacancy/{vacancy_id}")
  @GET
  public Response getVacancy(@PathParam("vacancy_id") Integer id) {
    try {
      ru.hh.school.hhapiclient.Vacancy hhVacancy = hhClient.getVacancy(Integer.toString(id));
      return Response.status(200).entity(
              ru.hh.school.dto.Vacancy.fromHHVacancy(hhVacancy)
      ).build();
    } catch (Exception e) {
      logger.warn("got exception " + e);
      return Response.status(500).build();
    }
  }

  @Path("/employer")
  @GET
  public Response getEmployers(@QueryParam("query") String query, @DefaultValue("0") @QueryParam("page") int page, @DefaultValue("20") @QueryParam("per_page") int per_page) {
    try {
      List<ru.hh.school.hhapiclient.Employer> hhEmployers = hhClient.getEmployers(query, page, per_page);
      return Response.status(200).entity(
              hhEmployers.stream()
              .map(hhEmployer -> ru.hh.school.dto.Employer.fromHHEmployer(hhEmployer)).collect(Collectors.toList())
      ).build();
    } catch (Exception e) {
      logger.warn("got exception " + e);
      return Response.status(500).build();
    }
  }

  @Path("/employer/{employer_id}")
  @GET
  public Response getEmployer(@PathParam("employer_id") Integer id) {
    try {
      ru.hh.school.hhapiclient.Employer hhEmployer = hhClient.getEmployer(Integer.toString(id));
      return Response.status(200).entity(
              ru.hh.school.dto.EmployerDetail.fromHHEmployer(hhEmployer)
      ).build();
    } catch (Exception e) {
      logger.warn("got exception " + e);
      return Response.status(500).build();
    }
  }
}
