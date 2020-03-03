package routes;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.net.SocketFlow;
import service.VacancyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/api/negotiation")
public class ApiNegotiation {
    private final VacancyService vacancyService;

    @Inject
    public ApiNegotiation(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    static class AddNegotiationRequest {
        @JsonProperty("resumeId")
        Integer resumeId;

        @JsonProperty("vacancyId")
        Integer vacancyId;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addResponse(AddNegotiationRequest addRequest) {
        Response.ResponseBuilder response;

        try {
            vacancyService.addNegotiation(addRequest.resumeId, addRequest.vacancyId);
            response =  Response.ok("OK");
        } catch (Exception E) {
            response =  Response.status(Response.Status.CONFLICT);
        }

        return response.build();
    }

}
