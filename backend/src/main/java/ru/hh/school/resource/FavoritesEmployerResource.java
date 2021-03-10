package ru.hh.school.resource;

import ru.hh.school.dto.request.FavoritesEmployerRequestDto;
import ru.hh.school.dto.request.FavoritesEmployerUpdateCommentDto;
import ru.hh.school.dto.response.ErrorResponseDto;
import ru.hh.school.dto.response.FavoritesEmployerResponseDto;
import ru.hh.school.entity.FavoritesEmployer;
import ru.hh.school.exception.ConstraintException;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.exception.InternalException;
import ru.hh.school.mapper.FavoritesEmployerMapper;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.FavoritesEmployerService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/favorites/employer")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class FavoritesEmployerResource {

    private final FavoritesEmployerService favoritesEmployerService;
    private final EmployerService employerService;

    private final FavoritesEmployerMapper responseMapper;

    public FavoritesEmployerResource(FavoritesEmployerService favoritesEmployerService, EmployerService employerService, FavoritesEmployerMapper responseMapper) {
        this.favoritesEmployerService = favoritesEmployerService;
        this.employerService = employerService;
        this.responseMapper = responseMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid FavoritesEmployerRequestDto request) {
        FavoritesEmployer favoritesEmployer = null;
        try {
            favoritesEmployer = favoritesEmployerService.create(request.getEmployerId(), request.getComment());
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
        FavoritesEmployerResponseDto employer = responseMapper.map(favoritesEmployer);
        employer.ClearWithoutId();
        return Response
                .status(Response.Status.CREATED)
                .entity(employer)
                .build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("limit") Integer limit,
                           @QueryParam("page") Integer page) {
        List<FavoritesEmployer> favoritesEmployers = null;
        try {
            favoritesEmployers = favoritesEmployerService.getAll(limit, page);
        } catch (InternalException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }

        List<FavoritesEmployerResponseDto> employersDtoList = favoritesEmployers.stream()
                .map(responseMapper::map)
                .collect(Collectors.toList());

        return Response
                .ok()
                .entity(employersDtoList)
                .build();
    }

    @PUT
    @Path(value = "/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(value = "id") Integer id,
                           @Valid FavoritesEmployerUpdateCommentDto commentDto) {
        favoritesEmployerService.update(id, commentDto.getComment());
        return Response
                .ok()
                .build();
    }

    @DELETE
    @Path(value = "/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam(value = "id") Integer id) {
        try {
            favoritesEmployerService.delete(id);
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
    public Response updateEmployer(@PathParam(value = "id") Integer id) {
        try {
            employerService.update(id);
        } catch (HhRequestException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }
        return Response
                .ok()
                .build();
    }
}
