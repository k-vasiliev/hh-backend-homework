package ru.hh.backend.homework.resource;

import ru.hh.backend.homework.dto.NegotiationRequestDto;
import ru.hh.backend.homework.dto.NegotiationResponseDto;
import ru.hh.backend.homework.mapper.NegotiationMapper;
import ru.hh.backend.homework.service.NegotiationService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/negotiation")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class NegotiationResource {
    private final NegotiationService negotiationService;
    private final NegotiationMapper negotiationMapper;

    @Inject
    public NegotiationResource(NegotiationService negotiationService, NegotiationMapper negotiationMapper) {
        this.negotiationService = negotiationService;
        this.negotiationMapper = negotiationMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public NegotiationResponseDto create(NegotiationRequestDto negotiationRequestDto) {
        return negotiationMapper.map(negotiationService.save(negotiationMapper.map(negotiationRequestDto)));
    }
}
