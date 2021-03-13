package ru.hh.school.controller.employer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.company.CompanyDtoByIdResponse;
import ru.hh.school.dto.company.CompanyDtoResponseArray;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Map;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/employer")
public class EmployerController {

    private static final Logger logger = LoggerFactory.getLogger(EmployerController.class);
    private final String EMPLOYER_URL = "https://api.hh.ru/employers";
    private final Client client;

    public EmployerController(Client client) {
        this.client = client;
    }

    @GET
    public Response getEmployers(@Context UriInfo allUri) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(EMPLOYER_URL).append("?");
        MultivaluedMap<String, String> mpAllQueParams = allUri.getQueryParameters();
        for (Map.Entry<String, List<String>> entry : mpAllQueParams.entrySet()) {
            entry.getValue().forEach(v -> stringBuffer.append(entry.getKey()).append("=").append(v).append("&"));
        }
        Response response = client.target(stringBuffer.toString()).request().get();
        int status = response.getStatus();
        CompanyDtoResponseArray entity = response.readEntity(CompanyDtoResponseArray.class);
        logger.info("get employers list");
        return Response.status(status).entity(entity.getItems()).build();
    }

    @GET
    @Path("/{employer_id}")
    public Response getVacancy(@PathParam("employer_id") Integer employerId) {
        Response response = client.target(EMPLOYER_URL + "/" + employerId).request().get();
        int status = response.getStatus();
        CompanyDtoByIdResponse entity = response.readEntity(CompanyDtoByIdResponse.class);
        logger.info("get employers info by id");
        return Response.status(status).entity(entity).build();
    }
}

