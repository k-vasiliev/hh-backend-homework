package ru.hh.school.resource;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.module.ResolutionException;

@Singleton
@Path("/employer")
public class EmployerResource {

    @GET
    @Path("/id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployers() {
        return Response.ok().build();
    }
}
