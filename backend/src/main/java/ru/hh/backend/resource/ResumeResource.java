package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.ResumeDao;
import ru.hh.backend.dto.request.ResumeRequestDto;
import ru.hh.backend.dto.response.ResumeResponseDto;
import ru.hh.backend.mapper.ResumeMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/resume")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class ResumeResource {

    private final ResumeMapper resumeMapper;
    private final ResumeDao resumeDao;

    public ResumeResource(ResumeMapper resumeMapper, ResumeDao resumeDao) {
        this.resumeMapper = resumeMapper;
        this.resumeDao = resumeDao;
    }

    @GET
    public List<ResumeResponseDto> getAll() {
        return resumeDao.getAll().stream().map(resumeMapper::map).collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResumeResponseDto create(ResumeRequestDto resumeRequestDto) {
        return resumeMapper.map(resumeDao.create(resumeMapper.map(resumeRequestDto)));
    }
}
