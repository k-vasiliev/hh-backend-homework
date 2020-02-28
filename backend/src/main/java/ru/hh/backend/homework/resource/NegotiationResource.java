package ru.hh.backend.homework.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.homework.dto.request.NegotiationRequestDto;
import ru.hh.backend.homework.dto.response.NegotiationResponseDto;
import ru.hh.backend.homework.entity.NegotiationEntity;
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
@Controller
public class NegotiationResource {
    private final NegotiationMapper negotiationMapper;
    private final NegotiationService negotiationService;

    public NegotiationResource(NegotiationMapper negotiationMapper, NegotiationService negotiationService) {
        this.negotiationMapper = negotiationMapper;
        this.negotiationService = negotiationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public NegotiationResponseDto create(NegotiationRequestDto negotiationRequestDto) {
        NegotiationEntity negotiation = negotiationService.create(negotiationMapper.map(negotiationRequestDto));
        return negotiationMapper.map(negotiation);
    }
}
