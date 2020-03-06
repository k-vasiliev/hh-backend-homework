package ru.hh.school.resource;

import ru.hh.school.dto.request.NegotiationRequestDto;
import ru.hh.school.service.NegotiationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/api/negotiation")
public class NegotiationResource {

    private NegotiationService negotiationService;

    @Inject
    public NegotiationResource(NegotiationService negotiationService) {
        this.negotiationService = negotiationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(NegotiationRequestDto negotiationDto) {
        negotiationService.saveNew(negotiationDto);
    }
}
