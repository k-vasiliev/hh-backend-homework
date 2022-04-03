package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hh.school.resource.mapper.EmployerMapper;
import ru.hh.school.service.EmployerService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Singleton
@Path("/employer")
@Produces("application/json")
public class EmployerController {
    private static final Logger logger = LoggerFactory.getLogger(EmployerController.class);
    public static final String DEFAULT_PAGE_PARAM = "0";
    public static final String DEFAULT_PER_PAGE_PARAM = "10";

    private final EmployerService employerService;
    private final EmployerMapper employerMapper;

    @Autowired
    public EmployerController(EmployerService employerService, EmployerMapper employerMapper) {
        this.employerService = employerService;
        this.employerMapper = employerMapper;
    }

    /**
     * Получение компаний
     *
     * @param query - текст для поиска
     * @return массив из компаний
     */
    @GET
    public Response getEmployers(@QueryParam("query") String query,
                                 @DefaultValue(DEFAULT_PAGE_PARAM) @QueryParam("page") Integer page,
                                 @DefaultValue(DEFAULT_PER_PAGE_PARAM) @QueryParam("per_page") Integer perPage) {
        return Response.ok(employerMapper.toEmployersDto(employerService.getHHEmployers(query, page, perPage))).build();
    }

    /**
     * Получение компании
     *
     * @param employerId - идентификатор работника
     * @return работник
     */
    @GET
    @Path(value = "/{employer_id}")
    public Response getEmployer(@PathParam("employer_id") Long employerId) {
        logger.info("getEmployers {}", employerId);
        return Response.ok(employerMapper.toEmployerDto(employerService.getHHEmployerById(employerId))).build();
    }
}