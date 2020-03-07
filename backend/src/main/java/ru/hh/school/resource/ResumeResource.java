package ru.hh.school.resource;

import ru.hh.school.dto.request.ResumeRequestDto;
import ru.hh.school.dto.response.ResumeResponseDto;
import ru.hh.school.service.ResumeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/resume")
public class ResumeResource {

    private ResumeService resumeService;

    @Inject
    public ResumeResource(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResumes() {
        List<ResumeResponseDto> resumesDto = resumeService.getAll();
        return Response.ok(resumesDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createResume(ResumeRequestDto resumeDto) {
        resumeService.saveNew(resumeDto);
        return Response.ok().build();
    }
}
