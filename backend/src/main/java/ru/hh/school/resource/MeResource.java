package ru.hh.school.resource;

import ru.hh.school.service.HhService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/me")
public class MeResource {

    private final HhService meService;

    public MeResource(HhService meService) {
        this.meService = meService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response me() {
        String me = meService.getMe();

        return Response.ok(me).build();
    }

}
