package ru.hh.nab.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dto.CreateVacancyDTO;
import ru.hh.nab.dto.ResponseVacancyDTO;
import ru.hh.nab.entity.Vacancy;
import ru.hh.nab.service.VacancyService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/vacancy")
@Singleton
public class VacancyResource {

    private static Logger logger = LoggerFactory.getLogger(VacancyResource.class);

    private final VacancyService vacancyService;

    public VacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResponseVacancyDTO> getAllVacancy() {
        return vacancyService.getAllVacancy();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseVacancyDTO getVacancyById(@PathParam("id") int id) {
        return vacancyService.getResponseVacancyById(id);
    }

    @POST
    @Consumes(value = "application/json")
    public List<Vacancy> createVacancy(@Valid @RequestBody CreateVacancyDTO body) {
        List<Vacancy> vacancy = Collections.emptyList();
        try {
            vacancy = Collections.singletonList(
                    vacancyService.createVacancy(Integer.valueOf(body.getCompanyId()), body.getTitle(),
                            body.getSalary(), body.getDescription(), body.getContacts())
            );
            logger.info(String.format(
                    "Create Vacancy by Title: %s, CompanyId: %s",
                    body.getTitle(), body.getCompanyId()
            ));
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return vacancy;
    }
}
