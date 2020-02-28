package ru.hh.back.resource;

import ru.hh.back.dao.ResumeDao;
import ru.hh.back.dto.ResumeCreateDto;
import ru.hh.back.service.Mapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/api/resume")
public class ResumeResource {

    private ResumeDao resumeDao;

    public ResumeResource(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResume() {
        var resume = resumeDao.getResume();
        var usersDto = resume.stream().map(Mapper::map).collect(Collectors.toList());
        return Response.ok(usersDto).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createResume(ResumeCreateDto resumeEntity) {
        Integer resumeId = resumeDao.save(Mapper.map(resumeEntity));
        return Response.ok(resumeId).build();
    }
}


