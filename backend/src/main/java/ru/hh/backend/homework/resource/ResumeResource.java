package ru.hh.backend.homework.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.homework.dto.request.ResumeRequestDto;
import ru.hh.backend.homework.dto.response.ResumeResponseDto;
import ru.hh.backend.homework.entity.ResumeEntity;
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
@Controller
public class ResumeResource {
    private final ResumeMapper resumeMapper;
    private final ResumeService resumeService;

    public ResumeResource(ResumeMapper resumeMapper, ResumeService resumeService) {
        this.resumeMapper = resumeMapper;
        this.resumeService = resumeService;
    }

    @GET
    public List<ResumeResponseDto> getAll() {
        List<ResumeEntity> resumes = resumeService.getAll();
        return resumes.stream().map(resumeMapper::map).collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResumeResponseDto create(ResumeRequestDto resumeRequestDto) {
        ResumeEntity resume = resumeService.create(resumeMapper.map(resumeRequestDto));
        return resumeMapper.map(resume);
    }
}
