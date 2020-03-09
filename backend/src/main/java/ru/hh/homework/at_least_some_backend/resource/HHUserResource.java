package ru.hh.homework.at_least_some_backend.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.hh.homework.at_least_some_backend.Utils;
import ru.hh.homework.at_least_some_backend.dto.HHUserDto;
import ru.hh.homework.at_least_some_backend.entity.HHUser;
import ru.hh.homework.at_least_some_backend.service.HHUserService;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Path("/user")
public class HHUserResource
{
    @Inject
    private HHUserService service;

    // @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("id") Long id)
    {
        return Utils.dtoToResponse(
                HHUserDto.entityToResponseDto(service.queryById(id))
        );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HHUserDto> getAllOfType(@QueryParam("type") String typeStr) throws WebApplicationException
    {
        var type = Utils.requireEnum(HHUser.UserType.class, typeStr, "type");

        return service.queryAllByType(type)
                .stream()
                .map(HHUserDto::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
