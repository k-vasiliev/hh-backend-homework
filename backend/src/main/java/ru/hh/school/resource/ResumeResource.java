package ru.hh.school.resource;

import ru.hh.school.dto.ResumeRequestDto;
import ru.hh.school.models.Resume;
import ru.hh.school.services.ResumeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/user?type=applicant")
public class ResumeResource {

    private final ResumeService resumeService;

    public ResumeResource(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Resume> getAllResumes() {
        return resumeService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(ResumeRequestDto resumeDto) {
        resumeService.saveNew(resumeDto);
    }
}
