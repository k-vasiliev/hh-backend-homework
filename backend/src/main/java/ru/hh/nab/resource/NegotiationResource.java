package ru.hh.nab.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dto.CreateNegotiationDTO;
import ru.hh.nab.dto.ResponseNegotiationDTO;
import ru.hh.nab.entity.Negotiation;
import ru.hh.nab.service.NegotiationService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/negotiation")
@Singleton
public class NegotiationResource {

    private static Logger logger = LoggerFactory.getLogger(CompanyResource.class);

    private final NegotiationService service;

    public NegotiationResource(NegotiationService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResponseNegotiationDTO> getAllNegotiations() {
        return service.getAllNegotiations();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ResponseNegotiationDTO getNegotiationById(@PathParam("id") Integer id) {
        return service.getNegotiationById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Negotiation> createNegotiation(@Valid @RequestBody CreateNegotiationDTO body) {
        List<Negotiation> negotiation = Collections.emptyList();
        try {
            negotiation = Collections.singletonList(
                    service.createNegotiation(body.getResumeId(), body.getVacancyId())
            );
            logger.info(
                    String.format(
                            "Create Negotiation by ResumeId: %s, VacancyId: %s",
                            body.getResumeId(), body.getVacancyId()
                    )
            );
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return negotiation;
    }
}
