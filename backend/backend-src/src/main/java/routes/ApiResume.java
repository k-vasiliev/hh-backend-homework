package routes;

import dao.ResumeDao;
import dto.ResumeDto;
import dto.VacancyDto;
import entity.ResumeEntity;
import service.ResumeMapper;
import service.ResumeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/resume")
public class ApiResume {
    private final ResumeMapper    resumeMapper;
    private final ResumeDao       resumeDao;
    private final ResumeService   resumeService;

    @Inject
    public ApiResume(ResumeService resumeService, ResumeDao resumeDao, ResumeMapper resumeMapper) {
        this.resumeService = resumeService;
        this.resumeDao = resumeDao;
        this.resumeMapper = resumeMapper;
    }

    @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resumeById(@PathParam("id") Integer id) {
        return Response.ok(new ResumeDto("r1","vasya","abcd")).build();
    }

    @GET
    @Path(value = "/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resumeAll() {
        List<ResumeDto> resumes = resumeService.getResumes()
                .stream()
                .map(ResumeMapper::toResumeDto)
                .collect(Collectors.toList());
        return Response.ok(resumes).build();
    }


}
