package ru.hh.school.resource;

import ru.hh.school.dto.VacancyRequestDto;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.service.VacancyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/vacancy")
public class VacancyResource {

    private final VacancyService vacancyService;

    @Inject
    public VacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vacancy> getAllVacancies() {
        return vacancyService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(VacancyRequestDto vacancyDto) {
        vacancyService.saveNew(vacancyDto);
    }
}
