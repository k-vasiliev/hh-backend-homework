package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.ResumeDao;
import ru.hh.backend.dto.request.ResumeRequestDto;
import ru.hh.backend.dto.response.ResumeResponseDto;
import ru.hh.backend.entity.Resume;
import ru.hh.backend.service.ResumeMapper;
import ru.hh.backend.service.ResumeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/resume")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class ResumeController {
    private final ResumeMapper resumeMapper;
    private final ResumeService resumeService;

    public ResumeController(ResumeMapper resumeMapper, ResumeService resumeService) {
        this.resumeMapper = resumeMapper;
        this.resumeService = resumeService;
    }

    @GET
    public List<ResumeResponseDto> getAll() {
        List<Resume> resumes = resumeService.getAll();
        return resumes.stream().map(resumeMapper::map).collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResumeResponseDto create(ResumeRequestDto resumeRequestDto) {
        Resume resume = resumeService.create(resumeMapper.map(resumeRequestDto));
        return resumeMapper.map(resume);
    }
}
