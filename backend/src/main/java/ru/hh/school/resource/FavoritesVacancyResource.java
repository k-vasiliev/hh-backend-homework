package ru.hh.school.resource;

import ru.hh.school.dto.request.FavoritesEmployerUpdateCommentDto;
import ru.hh.school.dto.request.FavoritesVacancyRequestDto;
import ru.hh.school.dto.response.ErrorResponseDto;
import ru.hh.school.dto.response.FavoritesVacancyResponseDto;
import ru.hh.school.entity.FavoritesVacancy;
import ru.hh.school.exception.ConstraintException;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.exception.InternalException;
import ru.hh.school.mapper.FavoritesVacancyMapper;
import ru.hh.school.service.FavoritesVacancyService;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/favorites/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class FavoritesVacancyResource {

    private final FavoritesVacancyService favoritesVacancyService;

    private final VacancyService vacancyService;

    private final FavoritesVacancyMapper favoritesVacancyMapper;

    public FavoritesVacancyResource(FavoritesVacancyService favoritesVacancyService, VacancyService vacancyService, FavoritesVacancyMapper favoritesVacancyMapper) {
        this.favoritesVacancyService = favoritesVacancyService;
        this.vacancyService = vacancyService;
        this.favoritesVacancyMapper = favoritesVacancyMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid FavoritesVacancyRequestDto request) {
        FavoritesVacancy favoritesVacancy;

        try {
            favoritesVacancy = favoritesVacancyService.create(request.getVacancyId(), request.getComment());
        } catch (HhRequestException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        } catch (ConstraintException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(error)
                    .build();
        }

        FavoritesVacancyResponseDto vacancy = favoritesVacancyMapper.map(favoritesVacancy);
        vacancy.clearWithoutId();
        return Response
                .status(Response.Status.CREATED)
                .entity(vacancy)
                .build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("limit") Integer limit,
                           @QueryParam("page") Integer page) {
        List<FavoritesVacancy> favoritesVacancyList;
        try {
            favoritesVacancyList = favoritesVacancyService.getAll(limit, page);
        } catch (InternalException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }

        List<FavoritesVacancyResponseDto> vacancyResponseDtoList = favoritesVacancyList.stream()
                .map(favoritesVacancyMapper::map)
                .collect(Collectors.toList());

        return Response
                .ok()
                .entity(vacancyResponseDtoList)
                .build();
    }

    @PUT
    @Path(value = "/{id}")
    public Response update(@PathParam(value = "id") Integer id,
                           @Valid FavoritesEmployerUpdateCommentDto commentDto) {
        favoritesVacancyService.update(id, commentDto.getComment());
        return Response
                .ok()
                .build();
    }

    @DELETE
    @Path(value = "/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam(value = "id") Integer id) {
        try {
            favoritesVacancyService.delete(id);
        } catch (InternalException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }

        return Response
                .ok()
                .build();
    }

    @POST
    @Path(value = "/{id}/refresh")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateVacancy(@PathParam(value = "id") Integer id) {
        try {
            vacancyService.update(id);
        } catch (HhRequestException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }
        return Response
                .ok()
                .build();
    }
}
