package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.NegotiationDao;
import ru.hh.backend.dto.request.NegotiationRequestDto;
import ru.hh.backend.dto.response.NegotiationResponseDto;
import ru.hh.backend.mapper.NegotiationMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/api/negotiation")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class NegotiationResource {

    private final NegotiationMapper negotiationMapper;
    private final NegotiationDao negotiationDao;

    public NegotiationResource(NegotiationDao negotiationDao, NegotiationMapper negotiationMapper) {
        this.negotiationDao = negotiationDao;
        this.negotiationMapper = negotiationMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public NegotiationResponseDto create(NegotiationRequestDto negotiationRequestDto) {
        return negotiationMapper.map(negotiationDao.create(negotiationMapper.map(negotiationRequestDto)));
    }
}
