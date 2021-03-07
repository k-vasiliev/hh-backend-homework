package ru.hh.school.resource;

import ru.hh.school.dto.EmployerItems;
import ru.hh.school.service.EmployerService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;

@Singleton
@Path("/employer")
public class EmployerResource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployers(@QueryParam("page") String page,
                                 @QueryParam("per_page") String perPage,
                                 @QueryParam("query") String query) throws InterruptedException, IOException, URISyntaxException {
        return Response
                .ok()
                .entity(EmployerService.getEmployers(page, perPage, query))
                .build();
    }
}
