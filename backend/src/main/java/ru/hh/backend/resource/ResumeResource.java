package ru.hh.backend.resource;

import javassist.NotFoundException;
import ru.hh.backend.dto.ResumeDtoRequest;
import ru.hh.backend.mapper.ResumeMapper;
import ru.hh.backend.service.ResumeService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/resume")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ResumeResource {

    private ResumeService resumeService;
    private ResumeMapper resumeMapper;

    public ResumeResource(ResumeService resumeService, ResumeMapper resumeMapper) {
        this.resumeService = resumeService;
        this.resumeMapper = resumeMapper;
    }

    @GET
    public Response getResumes() {
        return Response.ok(
                resumeService.getResumes().stream().map(resumeMapper::map).collect(Collectors.toList())
        ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createResume(@Valid ResumeDtoRequest resumeDtoRequest) {
        try {
            return Response.ok(
                    resumeService.save(resumeMapper.map(resumeDtoRequest))
            ).build();
        } catch (NotFoundException e) {
            return Response.status(404, e.getMessage()).build();
        }
    }
}
