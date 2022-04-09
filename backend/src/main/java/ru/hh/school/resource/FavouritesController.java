package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hh.school.domain.Employer;
import ru.hh.school.domain.Favourite;
import ru.hh.school.domain.Vacancy;
import ru.hh.school.resource.dto.*;
import ru.hh.school.resource.mapper.FavouriteMapper;
import ru.hh.school.service.AreaService;
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
    private final VacancyService vacancyService;
    private final FavouriteService favouriteService;
    private final FavouriteMapper favouriteMapper;
    private final AreaService areaService;

    @Autowired
    public FavouritesController(EmployerService employerService, VacancyService vacancyService,
                                FavouriteService favouriteService, FavouriteMapper favouriteMapper, AreaService areaService) {
        this.employerService = employerService;
        this.vacancyService = vacancyService;
        this.favouriteService = favouriteService;
        this.favouriteMapper = favouriteMapper;
        this.areaService = areaService;
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
        FavouriteEmployerResponseDto response = favouriteMapper.toFavoriteEmployerResponseDto(favouriteEmployer);
        response.setArea(areaService.getAreaData(favouriteEmployer.getEmployer().getAreaId()));
        return Response.ok(response).build();
    }

    /**
     * Получение избранных компании
     */
    @GET
    @Path(value = "/employer")
    public Response getFavouriteEmployers(@DefaultValue(DEFAULT_PAGE_PARAM) @QueryParam("page") Integer page,
                                          @DefaultValue(DEFAULT_PER_PAGE_PARAM) @QueryParam("per_page") Integer perPage) {
        List<Favourite> favouriteEmployers = favouriteService.getEmployers(page, perPage);
        Long favouriteEmployersTotalCount = favouriteService.countEmployers();
        favouriteService.incrementViews(favouriteEmployers);
        logger.info("getFavouriteEmployers");
        List<FavouriteEmployerResponseDto> employerDtoList =
                favouriteEmployers.stream().map(favouriteMapper::toFavoriteEmployerResponseDto).collect(Collectors.toList());
        return Response.ok(new FavouriteEmployersResponseDto(employerDtoList, favouriteEmployersTotalCount, perPage, page)).build();
    }

    /**
     * Изменить информацию о избранной компании
     */
    @PUT
    @Path(value = "/employer/{employer_id}")
    public Response updateFavouriteEmployer(@PathParam("employer_id") Long employerId, FavouriteUpdateRequestDto updateDto) {
        Favourite response = favouriteService.updateFavouriteEmployer(employerId, updateDto.getComment());
        return Response.ok(favouriteMapper.toFavoriteEmployerResponseDto(response)).build();
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
        EmployerResponseDto response = favouriteMapper.toFavoriteEmployerDto(favouriteEmployer);
        response.setArea(areaService.getAreaData(favouriteEmployer.getAreaId()));
        return Response.ok(response).build();
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
        FavouriteVacancyResponseDto response = favouriteMapper.toFavouriteVacancyResponseDto(favouriteVacancy);
        response.setArea(areaService.getAreaData(favouriteVacancy.getVacancy().getAreaId()));
        return Response.ok(response).build();
    }

    /**
     * Изменить информацию о избранной вакансии
     */
    @PUT
    @Path(value = "/vacancy/{vacancy_id}")
    public Response updateFavouriteVacancy(@PathParam("vacancy_id") Long vacancyId, FavouriteUpdateRequestDto updateDto) {
        Favourite response = favouriteService.updateFavouriteVacancy(vacancyId, updateDto.getComment());
        return Response.ok(favouriteMapper.toFavouriteVacancyResponseDto(response)).build();
    }


    /**
     * Получение вакансий из избранного
     */
    @GET
    @Path(value = "/vacancy")
    public Response getFavouriteVacancies(@DefaultValue(DEFAULT_PAGE_PARAM) @QueryParam("page") Integer page,
                                          @DefaultValue(DEFAULT_PER_PAGE_PARAM) @QueryParam("per_page") Integer perPage) {
        logger.info("getFavouriteVacancies");
        List<Favourite> favouriteVacancies = favouriteService.getVacancies(page, perPage);
        Long favouriteVacanciesTotalCount = favouriteService.countVacancies();
        favouriteService.incrementViews(favouriteVacancies);
        List<FavouriteVacancyResponseDto> vacancyDtoList =
                favouriteVacancies.stream().map(favouriteMapper::toFavouriteVacancyResponseDto).collect(Collectors.toList());
        return Response.ok(new FavouriteVacanciesResponseDto(vacancyDtoList, favouriteVacanciesTotalCount, perPage, page)).build();
    }

    /**
     * Удалить вакансию из избранного
     */
    @DELETE
    @Path(value = "/vacancy/{vacancy_id}")
    public Response deleteFavouriteVacancy(@PathParam("vacancy_id") Long vacancyId) {
        favouriteService.deleteVacancyById(vacancyId);
        return Response.ok().build();
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
        VacancyResponseDto response = favouriteMapper.toFavoriteVacancyDto(favoriteVacancy);
        response.setArea(areaService.getAreaData(favoriteVacancy.getAreaId()));
        return Response.ok(favouriteMapper.toFavoriteVacancyDto(favoriteVacancy)).build();
    }
}
