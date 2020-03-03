package ru.hh.nab.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dto.CreateVacancyDTO;
import ru.hh.nab.dto.ResponseVacancyDTO;
import ru.hh.nab.entity.Vacancy;
import ru.hh.nab.service.VacancyService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    @POST
    @Consumes(value = "application/json")
    public Vacancy createVacancy(@Valid @RequestBody CreateVacancyDTO body) {
        logger.info("Create Vacancy");
        return vacancyService.createVacancy(Integer.valueOf(body.getCompanyId()), body.getTitle(),
                body.getSalary(), body.getDescription(), body.getContacts());
    }
}
