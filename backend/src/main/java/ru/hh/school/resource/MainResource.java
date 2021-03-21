package ru.hh.school.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Singleton
@Path("/")
public class MainResource {
    @GET
    public Response main() {
        return Response.ok("Favorites companies and vacancies").build();

    }

}
