package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Salary;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.exception.InvalidPaginationException;
import ru.hh.school.http.HhClient;
import ru.hh.school.service.EmployerService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Singleton
@Path("/")
public class ExampleResource {

    private final EmployerDao employerDao;
    private final AreaDao areaDao;
    private final VacancyDao vacancyDao;
    private final EmployerService employerService;

    public ExampleResource(EmployerDao employerDao, AreaDao areaDao, VacancyDao vacancyDao, EmployerService employerService) {
        this.employerDao = employerDao;
        this.areaDao = areaDao;
        this.vacancyDao = vacancyDao;
        this.employerService = employerService;
    }

    private static final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    @GET
    @Path("/area/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveArea() {
        Area area = new Area();
        area.setId(1);
        area.setName("Moscow");
        areaDao.save(area);
        area = areaDao.get(Area.class, 1);
        return Response.ok().entity(area).build();
    }

    @GET
    @Path("/employer/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dummy() {
        Employer test = new Employer();
        test.setId(1);
        test.setName("Employer name");
        Area area = areaDao.get(Area.class, 1);
        System.out.println(area);
        test.setArea(area);
        employerDao.save(test);
        logger.info("" + test);
        Employer employer = employerDao.get(Employer.class, 1);
        logger.info("" + employer);
        logger.info("Do nothing");
        return Response.ok().entity(employer).build();
    }

    @GET
    @Path("/vacancy/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancy() {
        Vacancy test = new Vacancy();
        test.setId(1);
        test.setName("Vacancy test");
        Area area = areaDao.get(Area.class, 1);
        test.setDateCreate(LocalDate.now());
        test.setArea(area);
        Employer employer = employerDao.get(Employer.class, 1);
        test.setEmployer(employer);
        Salary salary = new Salary();
        salary.setCurrency("RUB");
        salary.setFrom(1000);
        salary.setTo(2000);
        salary.setGross(false);
        test.setSalary(salary);
        vacancyDao.save(test);
        Vacancy vacancy = vacancyDao.get(Vacancy.class, 1);
        return Response.ok().entity(vacancy).build();
    }

}