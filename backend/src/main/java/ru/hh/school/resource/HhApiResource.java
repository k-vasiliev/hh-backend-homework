package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.service.HhApiService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Singleton
@Path("/")
public class HhApiResource {

    private final HhApiService hhApiService;

    private static final Logger logger = LoggerFactory.getLogger(HhApiResource.class);

    public HhApiResource(HhApiService hhApiService) {
        this.hhApiService = hhApiService;
    }

    @GET
    @Path("/employer/{employer_id}")
    @Produces("application/json")
    public Response getEmployer(@PathParam(value = "employer_id") Integer id) throws IOException, InterruptedException {
        logger.info("Get Employer with id: " + id + " from hh api");

        return Response.ok(hhApiService.getEmployer(id)).build();
    }

    @GET
    @Path("/employer")
    @Produces("application/json")
    public Response getEmployers(@QueryParam(value = "query") @DefaultValue("") String search,
                                 @QueryParam(value = "page") @DefaultValue("0") Integer page,
                                 @QueryParam(value = "per_page") @DefaultValue("20") Integer perPage) throws IOException, InterruptedException {
        logger.info("Get Employers from hh api");

        return Response.ok(hhApiService.getEmployers(search, page, perPage)).build();
    }

    @GET
    @Path("/vacancy/{vacancy_id}")
    @Produces("application/json")
    public Response getVacancy(@PathParam(value = "vacancy_id") Integer id) throws IOException, InterruptedException {
        logger.info("Get Vacancy with id: " + id + " from hh api");

        return Response.ok(hhApiService.getVacancy(id)).build();
    }

    @GET
    @Path("/vacancy")
    @Produces("application/json")
    public Response getVacancies(@QueryParam(value = "query") @DefaultValue("") String search,
                                 @QueryParam(value = "page") @DefaultValue("0") Integer page,
                                 @QueryParam(value = "per_page") @DefaultValue("20") Integer perPage) throws IOException, InterruptedException {
        logger.info("Get Vacancies from hh api");

        return Response.ok(hhApiService.getVacancies(search, page, perPage)).build();
    }
}
