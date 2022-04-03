package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hh.school.resource.mapper.VacancyMapper;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static ru.hh.school.resource.EmployerController.DEFAULT_PAGE_PARAM;
import static ru.hh.school.resource.EmployerController.DEFAULT_PER_PAGE_PARAM;

@Singleton
@Path("/vacancy")
@Produces("application/json")
public class VacancyController {
  private static final Logger logger = LoggerFactory.getLogger(VacancyController.class);

  private final VacancyService vacancyService;
  private final VacancyMapper vacancyMapper;

  @Autowired
  public VacancyController(VacancyService vacancyService, VacancyMapper vacancyMapper) {
    this.vacancyService = vacancyService;
    this.vacancyMapper = vacancyMapper;
  }

  /**
   * Получение вакансий
   * @param query - текст для поиска
   * @return массив из вакансий
   */
  @GET
  public Response getVacancies(@QueryParam("query") String query,
                               @DefaultValue(DEFAULT_PAGE_PARAM) @QueryParam("page") Integer page,
                               @DefaultValue(DEFAULT_PER_PAGE_PARAM) @QueryParam("per_page") Integer perPage) {
    return Response.ok(vacancyMapper.toVacanciesDto(vacancyService.getHHVacancies(query, page, perPage))).build();
  }

  /**
   * Получить вакансию
   * @param vacancyId - идентификатор вакансии
   * @return работник
   */
  @GET
  @Path(value = "/{vacancy_id}")
  public Response getVacancy(@PathParam("vacancy_id") Long vacancyId) {
    return Response.ok(vacancyMapper.toVacancyDto(vacancyService.getHHVacancyById(vacancyId))).build();
  }
}