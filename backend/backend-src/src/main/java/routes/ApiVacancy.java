package routes;

import dao.ResumeDao;
import dao.VacancyDao;
import dto.VacancyDto;
import entity.VacancyEntity;
import service.ResumeMapper;
import service.ResumeService;
import service.VacancyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/vacancy")
public class ApiVacancy {
    private final VacancyDao vacancyDao;
    private final VacancyService vacancyService;

    @Inject
    public ApiVacancy(VacancyDao vacancyDao, VacancyService vacancyService) {
        this.vacancyDao = vacancyDao;
        this.vacancyService = vacancyService;
    }



    @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vacancyById(@PathParam("id") Integer id) {
        return Response.ok("OK").build();

    }

    @GET
    @Path(value = "/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vacancyAll() {
        List<VacancyDto> vacancies = vacancyService.getVacancies()
                .stream()
                .map(VacancyDto::new)
                .collect(Collectors.toList());

        return Response.ok(vacancies).build();
    }


}
