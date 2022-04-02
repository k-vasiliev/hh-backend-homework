package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.resource.dto.EmployerResponseDto;
import ru.hh.school.resource.dto.EmployersResponseDto;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Singleton
@Path("/employer")
@Produces("application/json")
public class EmployerController {
  private static final Logger logger = LoggerFactory.getLogger(EmployerController.class);

  @GET
  @Path(value = "/test")
  public Response testPing() {
    logger.info("test");
    return Response.noContent().build();
  }

  /**
   * Получение компаний
   * @param query - текст для поиска
   * @return массив из компаний
   */
  @GET // todo Поддерживает пагинацию
  public Response getEmployers(String query) {
    logger.info("getEmployers");
    return Response.ok(new EmployersResponseDto(new ArrayList<>())).build();
  }

  /**
   * Получение компании
   * @param employerId - идентификатор работника
   * @return работник
   */
  @GET
  @Path(value = "/{employer_id}")
  public Response getEmployers(@PathParam("employer_id") Long employerId) {
    logger.info("getEmployers {}", employerId);
    return Response.ok(new EmployerResponseDto()).build();
  }
}