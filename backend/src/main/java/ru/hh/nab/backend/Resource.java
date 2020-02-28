package ru.hh.nab.backend;

import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dao.CompanyDAO;
import ru.hh.nab.dao.ResumeDAO;
import ru.hh.nab.dao.UserDAO;
import ru.hh.nab.dao.VacancyDAO;
import ru.hh.nab.dto.CreateCompanyDTO;
import ru.hh.nab.dto.CreateResumeDTO;
import ru.hh.nab.dto.CreateUserDTO;
import ru.hh.nab.dto.CreateVacancyDTO;
import ru.hh.nab.entity.Company;
import ru.hh.nab.entity.Resume;
import ru.hh.nab.entity.Users;
import ru.hh.nab.entity.Vacancy;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
public class Resource {

    private final UserDAO userDAO;
    private final ResumeDAO resumeDAO;
    private final CompanyDAO companyDAO;
    private final VacancyDAO vacancyDAO;

    public Resource(UserDAO userDAO, ResumeDAO resumeDAO, CompanyDAO companyDAO, VacancyDAO vacancyDAO) {
        this.userDAO = userDAO;
        this.resumeDAO = resumeDAO;
        this.companyDAO = companyDAO;
        this.vacancyDAO = vacancyDAO;
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Users> getUserByType(@DefaultValue("applicant") @QueryParam("type") String type) {
        return userDAO.getAllUsersByType(type);
    }

    @GET
    @Path("/company")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> getAllCompany() {
        return companyDAO.getAllCompany();
    }

    @GET
    @Path("/vacancy")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vacancy> getAllVacancy() {
        return vacancyDAO.getAllVacancy();
    }

    @GET
    @Path("/resume")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Resume> getAllResume() {
        return resumeDAO.getAllResume();
    }


    @POST
    @Path("/user")
    @Consumes(value = "application/json")
    public Users createUser(@Valid @RequestBody CreateUserDTO body) {
        return userDAO.addUser(body.getName(), body.getType());
    }

    @POST
    @Path("/resume")
    @Consumes(value = "application/json")
    public Resume createResume(@Valid @RequestBody CreateResumeDTO body) {
        return resumeDAO.addResume(Integer.valueOf(body.getUserId()),
                body.getWorkExperience(), body.getTitle(), body.getContacts());
    }

    @POST
    @Path("/company")
    @Consumes(value = "application/json")
    public Company createCompany(@Valid @RequestBody CreateCompanyDTO body) {
        return companyDAO.addCompany(Integer.valueOf(body.getUserId()), body.getName());
    }

    @POST
    @Path("/vacancy")
    @Consumes(value = "application/json")
    public Vacancy createCompany(@Valid @RequestBody CreateVacancyDTO body) {
        return vacancyDAO.addVacancy(Integer.valueOf(body.getCompanyId()), body.getTitle(),
                body.getSalary(), body.getDescription(), body.getContacts());
    }

}
