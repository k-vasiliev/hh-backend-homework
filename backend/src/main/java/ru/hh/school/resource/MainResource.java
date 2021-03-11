package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Singleton
@Path("/")
public class MainResource {

    private static final Logger logger = LoggerFactory.getLogger(MainResource.class);

    @GET
    public Response main() {
        logger.debug("Main Resource GET");

        return Response.ok("Favorites companies and vacancies").build();

    }

}
