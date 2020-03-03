package routes;

import com.fasterxml.jackson.annotation.JsonProperty;
import dao.ResumeDao;
import dto.NewResumeDto;
import dto.ResumeDto;
import service.ResumeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/resume")
public class ApiResume {
    private final ResumeService   resumeService;

    @Inject
    public ApiResume(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resumeById(@PathParam("id") Integer id) {
        return Response.ok("ok").build();
    }

    @GET
    @Path(value = "/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resumeAll() {
        List<ResumeDto> resumes = resumeService.getResumes()
                .stream()
                .map(ResumeDto::new)
                .collect(Collectors.toList());
        return Response.ok(resumes).build();
    }


    @POST
    @Path(value="/")
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response addResume(NewResumeDto resume) {
        resumeService.newResume(resume);
       return Response.ok().build();
    }

}
