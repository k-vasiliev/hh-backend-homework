package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hh.school.domain.Employer;
import ru.hh.school.domain.Favourite;
import ru.hh.school.domain.Vacancy;
import ru.hh.school.resource.dto.*;
import ru.hh.school.resource.mapper.EmployerMapper;
import ru.hh.school.resource.mapper.FavouriteMapper;
import ru.hh.school.resource.mapper.VacancyMapper;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.FavouriteService;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

import static ru.hh.school.resource.EmployerController.DEFAULT_PAGE_PARAM;
import static ru.hh.school.resource.EmployerController.DEFAULT_PER_PAGE_PARAM;

@Singleton
@Path("/favourites")
@Produces("application/json")
public class FavouritesController {
    private static final Logger logger = LoggerFactory.getLogger(FavouritesController.class);

    private final EmployerService employerService;
    private final EmployerMapper employerMapper;
    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;
    private final FavouriteService favouriteService;
    private final FavouriteMapper favouriteMapper;

    @Autowired
    public FavouritesController(EmployerService employerService, EmployerMapper employerMapper, VacancyService vacancyService,
                                VacancyMapper vacancyMapper, FavouriteService favouriteService, FavouriteMapper favouriteMapper) {
        this.employerService = employerService;
        this.employerMapper = employerMapper;
        this.vacancyService = vacancyService;
        this.vacancyMapper = vacancyMapper;
        this.favouriteService = favouriteService;
        this.favouriteMapper = favouriteMapper;
    }

    /**
     * Добавление компании в избранное
     *
     * @param employer - компания
     * @return
     */
    @POST
    @Path(value = "/employer")
    public Response addFavouriteEmployer(FavouriteEmployerRequestDto employer) {
        HHEmployerResponseDto hhEmployer = employerService.getHHEmployerById(employer.getEmployerId());
        if (hhEmployer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employer not found").build();
        }
        Favourite favouriteEmployer = favouriteService.addEmployer(hhEmployer, employer.getComment());
        logger.info("addFavouriteVacancy");
        return Response.ok(favouriteMapper.toFavoriteEmployerResponseDto(favouriteEmployer)).build();
    }

    /**
     * Получение избранных компании
     */
    @GET
    @Path(value = "/employer")
    public Response getFavouriteEmployers(@DefaultValue(DEFAULT_PAGE_PARAM) @QueryParam("page") Integer page,
                                          @DefaultValue(DEFAULT_PER_PAGE_PARAM) @QueryParam("per_page") Integer perPage) {
        List<Favourite> favouriteEmployers = favouriteService.getEmployers(page, perPage);
        logger.info("getFavouriteEmployers");
        return Response.ok(favouriteEmployers.stream().map(favouriteMapper::toFavoriteEmployerResponseDto).collect(Collectors.toList())).build();
    }

    /**
     * Изменить информацию о избранной компании
     */
    @PUT
    @Path(value = "/employer/{employer_id}")
    public Response updateFavouriteEmployer(@PathParam("employer_id") Long employerId, FavouriteEmployerUpdateRequestDto updateDto) {
        logger.info("updateFavouriteEmployer");
        return Response.ok(new FavouriteEmployersResponseDto()).build();
    }

    /**
     * Удалить компанию из избранного
     */
    @DELETE
    @Path(value = "/employer/{employer_id}")
    public Response deleteFavouriteEmployer(@PathParam("employer_id") Long employerId) {
        try {
            favouriteService.deleteEmployerById(employerId);
            logger.info("deleteFavouriteEmployer");
            return Response.ok().build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Favourite employer not found").build();
        }
    }

    /**
     * Обновить информацию о компании из избранного
     */
    @POST
    @Path(value = "/employer/{employer_id}/refresh")
    public Response refreshFavouriteEmployer(@PathParam("employer_id") Long employerId) {
        HHEmployerResponseDto hhEmployer = employerService.getHHEmployerById(employerId);
        if (hhEmployer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employer not found").build();
        }
        Employer favouriteEmployer = favouriteService.refreshEmployer(hhEmployer);
        if (favouriteEmployer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Favourite employer not found").build();
        }
        logger.info("refreshFavouriteEmployer");
        return Response.ok(favouriteMapper.toFavoriteEmployerDto(favouriteEmployer)).build();
    }

    /**
     * Добавить вакансию в избранное
     *
     * @param vacancy - вакансия
     * @return
     */
    @POST
    @Path(value = "/vacancy")
    public Response addFavouriteVacancy(FavouriteVacancyRequestDto vacancy) {
        HHVacancyResponseDto hhVacancy = vacancyService.getHHVacancyById(vacancy.getVacancyId());
        if (hhVacancy == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Vacancy not found").build();
        }
        Favourite favouriteVacancy = favouriteService.addVacancy(hhVacancy, vacancy.getComment());
        logger.info("addFavouriteVacancy");
        return Response.ok(favouriteMapper.toFavouriteVacancyResponseDto(favouriteVacancy)).build();
    }

    /**
     * Получение вакансий из избранного
     */
    @GET
    @Path(value = "/vacancy")
    public Response getFavouriteVacancies(@DefaultValue(DEFAULT_PAGE_PARAM) @QueryParam("page") Integer page,
                                          @DefaultValue(DEFAULT_PER_PAGE_PARAM) @QueryParam("per_page") Integer perPage) {
        logger.info("getFavouriteVacancies");
        List<Favourite> favouriteVacancies = favouriteService.getEmployers(page, perPage);
        return Response.ok(favouriteVacancies.stream().map(favouriteMapper::toFavouriteVacancyResponseDto).collect(Collectors.toList())).build();
    }

    /**
     * Удалить вакансию из избранного
     */
    @DELETE
    @Path(value = "/vacancy/{vacancy_id}")
    public Response deleteFavouriteVacancy(@PathParam("vacancy_id") Long vacancyId) {
        try {
            favouriteService.deleteVacancyById(vacancyId);
            logger.info("deleteFavouriteVacancy {}", vacancyId);
            return Response.ok().build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Favourite vacancy not found").build();
        }
    }

    /**
     * Обновить информацию о вакансии в избранном
     */
    @POST
    @Path(value = "/vacancy/{vacancy_id}/refresh")
    public Response refreshFavouriteVacancy(@PathParam("vacancy_id") Long vacancyId) {
        HHVacancyResponseDto hhVacancy = vacancyService.getHHVacancyById(vacancyId);
        if (hhVacancy == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Vacancy not found").build();
        }
        Vacancy favoriteVacancy = favouriteService.refreshVacancy(hhVacancy);
        if (favoriteVacancy == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Favourite vacancy not found").build();
        }
        logger.info("refreshFavouriteEmployer");
        return Response.ok(favouriteMapper.toFavoriteVacancyDto(favoriteVacancy)).build();
    }
}
