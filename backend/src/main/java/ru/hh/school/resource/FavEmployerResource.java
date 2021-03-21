package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.entity.Employer;
import ru.hh.school.service.EmployerService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Singleton
@Path("/favorites/employer")
public class FavEmployerResource {

    private final EmployerService employerService;

    private static final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    public FavEmployerResource(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GET
    @Produces("application/json")
    public Response getEmployers(@QueryParam(value = "page") @DefaultValue("0") Integer page,
                                 @QueryParam(value = "per_page") @DefaultValue("20") Integer perPage) {
        if (perPage < 0 || perPage > 100) return Response
                .status(400, "per_page can't be more than 100, and less than 0").build();

        List<Employer> employers = employerService.getEmployersFromFavorites(page, perPage);

        if(employers != null) return Response.ok(employers).build();

        return Response.status(400).build();
    }

    @POST
    public Response saveEmployer(@QueryParam(value = "employer_id") Integer id,
                                 @QueryParam(value = "comment") String comment) {
        logger.info("Saving employer to favorites with id: "
                + id + " and comment: " + comment);

        try {
            employerService.saveEmployerToFavorites(id, comment);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/{employer_id}")
    @Produces("application/json")
    public Response editEmployer(@PathParam(value = "employer_id") Integer id,
                                 @QueryParam(value = "comment") String comment) {
        logger.info("Editing employer with id: "
                + id + " and comment: " + comment);

        Employer employer = employerService.editEmployerFavorites(id, comment);

        if (employer != null) return Response.ok(employer).build();
        return Response.status(400, "Employer with id:" + id + " not found").build();
    }

    @DELETE
    @Path("/{employer_id}")
    @Produces("application/json")
    public Response deleteEmployer(@PathParam(value = "employer_id") Integer id) {
        logger.info("Deleting employer with id: " + id);

        Employer employer = employerService.deleteEmployerFavorites(id);

        if(employer != null) return Response.ok(employer).build();
        return Response.status(400, "Employer with id:" + id + " not found").build();
    }

    @POST
    @Path("/{employer_id}/refresh")
    @Produces("application/json")
    public Response refreshEmployer(@PathParam(value = "employer_id") Integer id) {
        logger.info("Refreshing employer with id: " + id);

        Employer employer = null;
        try {
            employer = employerService.refreshEmployerFavorites(id);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }

        if (employer != null) return Response.ok(employer).build();
        return Response.status(400, "Employer with id: " + id + " not found").build();
    }

}
