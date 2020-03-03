package ru.hh.backend.resource;

import javassist.NotFoundException;
import ru.hh.backend.dto.NegotiationDtoRequest;
import ru.hh.backend.mapper.NegotiationMapper;
import ru.hh.backend.service.NegotiationService;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/negotiation")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class NegotiationResource {

    private NegotiationService negotiationService;
    private NegotiationMapper negotiationMapper;

    public NegotiationResource(NegotiationService negotiationService, NegotiationMapper negotiationMapper) {
        this.negotiationService = negotiationService;
        this.negotiationMapper = negotiationMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNegotiation(NegotiationDtoRequest negotiationDtoRequest) {
        try {
            return Response.ok(
                    negotiationService.save(negotiationMapper.map(negotiationDtoRequest))
            ).build();
        } catch (NotFoundException e) {
            return Response.status(404, e.getMessage()).build();
        }
    }
}
