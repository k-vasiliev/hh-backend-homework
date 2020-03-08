package ru.hh.school.resource;


import ru.hh.school.dto.ResumeRequestDto;
import ru.hh.school.dto.ResumeResponseDto;
import ru.hh.school.mapper.ResumeMapper;
import ru.hh.school.service.ResumeService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/resume")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ResumeResource {
    private final ResumeService resumeService;
    private final ResumeMapper resumeMapper;


    public ResumeResource(ResumeService resumeService, ResumeMapper resumeMapper) {
        this.resumeService = resumeService;
        this.resumeMapper = resumeMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResumeResponseDto create(ResumeRequestDto resumeRequestDto) {
        return resumeMapper.map(resumeService.create(resumeMapper.map(resumeRequestDto)));
    }

    @GET
    public List<ResumeResponseDto> getAll() {
        return resumeService.getAll().stream().map(resumeMapper::map).collect(Collectors.toList());
    }
}
