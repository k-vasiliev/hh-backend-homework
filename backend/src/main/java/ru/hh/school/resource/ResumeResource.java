package ru.hh.school.resource;

import ru.hh.school.dto.request.CreateResumeDto;
import ru.hh.school.dto.response.ResumeDto;
import ru.hh.school.service.ResumeService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/resume")
public class ResumeResource {

    private ResumeService resumeService;

    @Inject
    public ResumeResource(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createdResume(CreateResumeDto dto) {
        resumeService.createResume(dto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResumeDto> getAllResumes() {
        return resumeService.getAllResumes();
    }
}
