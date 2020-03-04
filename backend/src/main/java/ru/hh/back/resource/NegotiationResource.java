package ru.hh.back.resource;

import ru.hh.back.dao.NegotiationDao;
import ru.hh.back.dto.NegotiationRequestDto;
import ru.hh.back.service.Mapper;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/negotiation")
public class NegotiationResource {

    private NegotiationDao negotiationDao;
    public NegotiationResource(NegotiationDao negotiationDao){
        this.negotiationDao = negotiationDao;
    }


    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNegotiation(NegotiationRequestDto negotiation) {
        // return Response.ok().build();
        Integer negotiationId = negotiationDao.save(Mapper.map(negotiation));
        return Response.ok(negotiationId).build();
    }
}


