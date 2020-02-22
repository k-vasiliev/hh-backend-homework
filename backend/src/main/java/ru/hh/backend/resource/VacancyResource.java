package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.model.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Path("/api/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class VacancyResource {


    @GET
    public List<Vacancy> getAll() {
        Company company = new Company();
        company.setId(1L);
        company.setName("hh");

        Vacancy vacancy = new Vacancy();
        vacancy.setContacts("Позвоните нам: 55-55-55");
        vacancy.setDescription("Описание к вакансии: лучшее предложение");
        vacancy.setTitle("Java девелопер");
//        vacancy.setDateCreate(LocalDateTime.now());
        vacancy.setId(1L);
        vacancy.setSalary(50000);
        vacancy.setNegotiations(Collections.emptyList());
        vacancy.setCompany(company);

        return Collections.singletonList(vacancy);
    }

    @GET
    @Path("/{id}")
    public Vacancy getById(@PathParam("id") String id) {
        Company company = new Company();
        company.setId(1L);
        company.setName("hh");

        Vacancy vacancy = new Vacancy();
        vacancy.setContacts("Позвоните нам: 55-55-55");
        vacancy.setDescription("Описание к вакансии: лучшее предложение");
        vacancy.setId(1L);
        vacancy.setSalary(50000);
        vacancy.setNegotiations(Collections.emptyList());
        vacancy.setCompany(company);

        return vacancy;
    }

    @GET
    @Path("/{id}/negotiations")
    public List<Negotiation> geNegotiations(@PathParam("id") String id) {
        User user = new User();
        user.setId(1L);
        user.setName("Денис");
        user.setUserType("APPLICANT");

        Resume resume = new Resume();
        resume.setId(1L);
        resume.setTitle("Программист");
        resume.setApplicant(user);

        Negotiation negotiation = new Negotiation();
        negotiation.setId(1L);
        negotiation.setResume(resume);

        return Collections.singletonList(negotiation);
    }
}
