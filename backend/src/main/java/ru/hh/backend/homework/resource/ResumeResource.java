package ru.hh.backend.homework.resource;

import ru.hh.backend.homework.dto.ResumeRequestDto;
import ru.hh.backend.homework.dto.ResumeResponseDto;
import ru.hh.backend.homework.mapper.ResumeMapper;
import ru.hh.backend.homework.service.ResumeService;

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

    @Inject
    public ResumeResource(ResumeService resumeService, ResumeMapper resumeMapper) {
        this.resumeService = resumeService;
        this.resumeMapper = resumeMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResumeResponseDto create(ResumeRequestDto resumeRequestDto) {
        return resumeMapper.map(resumeService.save(resumeMapper.map(resumeRequestDto)));
    }

    @GET
    public List<ResumeResponseDto> getAll() {
        return resumeService.getAll().stream().map(resumeMapper::map).collect(Collectors.toList());
    }
}
