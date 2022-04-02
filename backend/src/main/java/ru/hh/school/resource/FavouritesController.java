package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.resource.dto.FavouriteEmployerRequestDto;
import ru.hh.school.resource.dto.FavouriteEmployerUpdateRequestDto;
import ru.hh.school.resource.dto.FavouriteEmployersResponseDto;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Singleton
@Path("/favourites")
@Produces("application/json")
public class FavouritesController {
    private static final Logger logger = LoggerFactory.getLogger(FavouritesController.class);

    /**
     * Добавление компании в избранное
     *
     * @param employer - компания
     * @return
     */
    @POST
    @Path(value = "/employer")
    public Response addFavouriteEmployer(FavouriteEmployerRequestDto employer) {
        logger.info("getEmployers");
        return Response.ok("OK").build();
    }

    /**
     * Получение избранных компании
     */
    @GET
    @Path(value = "/employer") // todo Поддерживает пагинацию
    public Response getFavouriteEmployers() {
        logger.info("getFavouriteEmployers");
        return Response.ok(new FavouriteEmployersResponseDto(new ArrayList<>())).build();
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
        logger.info("deleteFavouriteEmployer");
        return Response.ok().build();
    }

    /**
     * Обновить информацию о компании из избранного
     */
    @POST
    @Path(value = "/employer/{employer_id}/refresh")
    public Response refreshFavouriteEmployer(@PathParam("employer_id") Long employerId) {
        logger.info("refreshFavouriteEmployer");
        return Response.ok(new FavouriteEmployersResponseDto()).build();
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
        logger.info("addFavouriteVacancy");
        return Response.ok("OK").build();
    }

    /**
     * Получение вакансий из избранного
     */
    @GET
    @Path(value = "/vacancy") // todo Поддерживает пагинацию
    public Response getFavouriteVacancies() {
        logger.info("getFavouriteVacancies");
        return Response.ok(new FavouriteVacanciesResponseDto(new ArrayList<>())).build();
    }

    /**
     * Удалить вакансию из избранного
     */
    @DELETE
    @Path(value = "/vacancy/{vacancy_id}")
    public Response deleteFavouriteVacancy(@PathParam("vacancy_id") Long vacancyId) {
        logger.info("deleteFavouriteVacancy {}", vacancyId);
        return Response.ok().build();
    }

    /**
     * Обновить информацию о вакансии в избранном
     */
    @POST
    @Path(value = "/vacancy/{vacancy_id}/refresh")
    public Response refreshFavouriteVacancy(@PathParam("vacancy_id") Long vacancyId) {
        logger.info("refreshFavouriteVacancy {}", vacancyId);
        return Response.ok(new FavouriteEmployersResponseDto()).build();
    }
}
