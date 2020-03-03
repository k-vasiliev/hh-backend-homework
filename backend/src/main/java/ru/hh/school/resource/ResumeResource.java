package ru.hh.school.resource;

import ru.hh.school.dto.ResumeRequestDto;
import ru.hh.school.dto.ResumeResponseDto;
import ru.hh.school.entity.Resume;
import ru.hh.school.service.ResumeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public List<ResumeResponseDto> getAllResumes() {
        return resumeService.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(ResumeRequestDto resumeDto) {
        resumeService.saveNew(resumeDto);
    }
}
