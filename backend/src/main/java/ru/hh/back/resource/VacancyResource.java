package ru.hh.back.resource;

import ru.hh.back.dao.NegotiationDao;
import ru.hh.back.dao.VacancyDao;
import ru.hh.back.dto.VacancyRequestDto;
import ru.hh.back.dto.VacancyResponseDto;
import ru.hh.back.service.Mapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/vacancy")
public class VacancyResource {
    private VacancyDao vacancyDao;
    private NegotiationDao negotiationDao;

    public VacancyResource(VacancyDao vacancyDao, NegotiationDao negotiationDao) {
        this.vacancyDao = vacancyDao;
        this.negotiationDao = negotiationDao;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VacancyResponseDto> getVacancy() {
        var vacancy = vacancyDao.getVacancy();
        var vacancyGetDtos = vacancy.stream().map(Mapper::map).collect(Collectors.toList());
        return vacancyGetDtos;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancy(@PathParam("id") Integer id) {
        var vacancy = vacancyDao.getVacancy(id);
        if (vacancy.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(Mapper.map(vacancy.get())).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVacancy(VacancyRequestDto vacancyDto) {
        Integer vacancyId = vacancyDao.save(Mapper.map(vacancyDto));
        return Response.ok(vacancyId).build();
    }

    @GET
    @Path("/{id}/negotiations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNegotiation(@PathParam("id") Integer id) {
        var negotiations = negotiationDao.getVacancyNegotiation(id);
        var negotiationsDto = negotiations.stream().map(Mapper::map).collect(Collectors.toList());
        return Response.ok(negotiationsDto).build();
    }
}
