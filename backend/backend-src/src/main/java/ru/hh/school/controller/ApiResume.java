package ru.hh.school.controller;

import ru.hh.school.dto.NewResumeDto;
import ru.hh.school.dto.ResumeDto;
import ru.hh.school.entity.ResumeEntity;
import ru.hh.school.service.ResumeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/resume")
public class ApiResume {
    private final ResumeService     resumeService;

    @Inject
    public ApiResume(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resumeById(@PathParam("id") Integer id) {
        ResumeDto resumeDto = new ResumeDto(resumeService.getResumeById(id));
        return Response.ok(resumeDto).build();
    }

    @GET
    @Path(value = "/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resumeAll() {
        List<ResumeEntity> resumeList = resumeService.getResumes();
        List<ResumeDto> resumes = resumeList
                .stream()
                .map(ResumeDto::new)
                .collect(Collectors.toList());
        return Response.ok(resumes).build();
    }


    @POST
    @Path(value="/")
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response addResume(NewResumeDto resume) {
       Integer resumeId = resumeService.newResume(resume);
       if (resumeId == -1)
           return Response.status(Response.Status.BAD_REQUEST).build();

       return Response.ok(resumeId).build();
    }

}
