package ru.hh.school.resource;

import ru.hh.school.dto.request.CreateVacancyDto;
import ru.hh.school.dto.response.VacancyDto;
import ru.hh.school.service.VacancyService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/vacancy")
public class VacancyResource {

    private VacancyService vacancyService;

    @Inject
    public VacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createVacancy(CreateVacancyDto dto) {
        vacancyService.createVacancy(dto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacancyDto> getALLVacancies() {
        return vacancyService.getAllVacancies();
    }

}
