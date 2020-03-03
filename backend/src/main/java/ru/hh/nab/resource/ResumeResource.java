package ru.hh.nab.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dto.CreateResumeDTO;
import ru.hh.nab.dto.ResponseResumeDTO;
import ru.hh.nab.entity.Resume;
import ru.hh.nab.service.ResumeService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/resume")
@Singleton
public class ResumeResource {

    private static Logger logger = LoggerFactory.getLogger(ResumeResource.class);

    private final ResumeService service;

    public ResumeResource(ResumeService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResponseResumeDTO> getAllResume() {
        return service.getAllResume();
    }

    @POST
    @Consumes(value = "application/json")
    public Resume createResume(@Valid @RequestBody CreateResumeDTO body) {
        logger.info("Create Resume");
        return service.createResume(Integer.valueOf(body.getUserId()),
                body.getWorkExperience(), body.getTitle(), body.getContacts());
    }

}
