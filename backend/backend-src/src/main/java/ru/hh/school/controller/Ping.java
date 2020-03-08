package ru.hh.school.controller;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/")
public class Ping {
    @GET
    public Response ping() {
        return Response.ok("Hello!").build();
    }

    @GET
    @Path(value = "/xxx")
    public Response pong(@QueryParam(value = "param") @DefaultValue(value="abc") String param) {
        return Response.ok("Param" + param).build();
    }

}

