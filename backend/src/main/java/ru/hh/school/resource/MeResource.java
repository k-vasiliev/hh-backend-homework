package ru.hh.school.resource;

import ru.hh.school.service.MainService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/me")
public class MeResource {

    private final MainService service;

    public MeResource(MainService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response me() {
        String me = service.getMe();

        return Response.ok(me).build();
    }

}