package ru.hh.back.resource;

import ru.hh.back.dto.ResumeRequestDto;
import ru.hh.back.service.ResumeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/resume")
public class ResumeResource {

    private ResumeService resumeService;

    public ResumeResource(ResumeService resumeDao) {
        this.resumeService = resumeDao;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResume() {
        var resumes = resumeService.getResume();
        return Response.ok(resumes).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createResume(ResumeRequestDto resume) {
        Integer resumeId = resumeService.saveResume(resume);
        return Response.ok(resumeId).build();
    }
}


