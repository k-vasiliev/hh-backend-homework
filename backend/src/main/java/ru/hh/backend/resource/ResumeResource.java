package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.model.Resume;
import ru.hh.backend.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Path("/api/resume")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class ResumeResource {

    @GET
    public List<Resume> getAll() {
        User user = new User();
        user.setId(1L);
        user.setName("Денис");
        user.setUserType("APPLICANT");

        Resume resume = new Resume();
        resume.setId(1L);
        resume.setTitle("Программист");
        resume.setApplicant(user);
//        resume.setDateCreate(LocalDateTime.now());
        return Collections.singletonList(resume);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Resume create(Resume resume) {
        resume.setId(1L);

        return resume;
    }
}
