package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.resource.dto.VacanciesResponseDto;
import ru.hh.school.resource.dto.VacancyResponseDto;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Singleton
@Path("/vacancy")
@Produces("application/json")
public class VacancyController {
  private static final Logger logger = LoggerFactory.getLogger(VacancyController.class);

  /**
   * Получение вакансий
   * @param query - текст для поиска
   * @return массив из вакансий
   */
  @GET // todo Поддерживает пагинацию
  public Response getVacancies(String query) {
    logger.info("getVacancies");
    return Response.ok(new VacanciesResponseDto(new ArrayList<>())).build();
  }

  /**
   * Получить вакансию
   * @param vacancyId - идентификатор вакансии
   * @return работник
   */
  @GET
  @Path(value = "/{vacancy_id}")
  public Response getVacancy(@PathParam("vacancy_id") Long vacancyId) {
    logger.info("getVacancy {}", vacancyId);
    return Response.ok(new VacancyResponseDto()).build();
  }
}