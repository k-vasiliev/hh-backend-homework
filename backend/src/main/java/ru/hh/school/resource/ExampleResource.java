package ru.hh.school.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Singleton
@Path("/")
public class ExampleResource {

    @GET
    public void dummy() {

    }
}
