package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.NegotiationDao;
import ru.hh.backend.dto.request.NegotiationRequestDto;
import ru.hh.backend.dto.response.NegotiationResponseDto;
import ru.hh.backend.entity.Negotiation;
import ru.hh.backend.service.NegotiationMapper;
import ru.hh.backend.service.NegotiationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/negotiation")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class NegotiationController {
    private final NegotiationMapper negotiationMapper;
    private final NegotiationService negotiationService;

    public NegotiationController(NegotiationMapper negotiationMapper, NegotiationService negotiationService) {
        this.negotiationMapper = negotiationMapper;
        this.negotiationService = negotiationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public NegotiationResponseDto create(NegotiationRequestDto negotiationRequestDto) {
        Negotiation negotiation = negotiationService.create(negotiationMapper.map(negotiationRequestDto));
        return negotiationMapper.map(negotiation);
    }
}
