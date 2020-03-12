package ru.hh.homework.at_least_some_backend.resource;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("hello")
public class HelloWorldResource
{
    @GET
    @Produces(MediaType.TEXT_HTML)
    public static String get(@QueryParam("name") @DefaultValue("anonym") String name)
    {
        return "<!DOCTYPE HTML><head><title>Hello, World!</title></head><body><header>Hello, World! Welcome, "
                + name
                + "!</header></body>";
    }
}
