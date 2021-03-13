package ru.hh.school.controller.vacancy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.vacancy.VacancyDtoResponse;
import ru.hh.school.dto.vacancy.VacancyDtoResponseArray;

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
@Path("/vacancy")
public class VacancyController {

    private static final Logger logger = LoggerFactory.getLogger(VacancyController.class);
    private final String VACANCY_URL = "https://api.hh.ru/vacancies";
    private final Client client;

    public VacancyController(Client client) {
        this.client = client;
    }

    @GET
    public Response getEmployers(@Context UriInfo allUri) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(VACANCY_URL).append("?");
        MultivaluedMap<String, String> mpAllQueParams = allUri.getQueryParameters();
        for (Map.Entry<String, List<String>> entry : mpAllQueParams.entrySet()) {
            entry.getValue().forEach(v -> stringBuffer.append(entry.getKey()).append("=").append(v).append("&"));
        }
        logger.info(stringBuffer.toString());
        Response response = client.target(stringBuffer.toString()).request().get();
        int status = response.getStatus();
        VacancyDtoResponseArray entity = response.readEntity(VacancyDtoResponseArray.class);
        logger.info("get vacancies list");
        return Response.status(status).entity(
                entity.getItems()
        ).build();
    }

    @GET
    @Path("/{vacancy_id}")
    public Response getVacancy(@PathParam("vacancy_id") Integer vacancyId) {
        Response response = client.target(VACANCY_URL + "/" + vacancyId).request().get();
        int status = response.getStatus();
        VacancyDtoResponse entity = response.readEntity(VacancyDtoResponse.class);
        logger.info("get vacancies info by id");
        return Response.status(status).entity(entity).build();
    }
}
