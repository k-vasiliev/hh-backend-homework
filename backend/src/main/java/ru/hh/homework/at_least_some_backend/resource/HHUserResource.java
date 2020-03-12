package ru.hh.homework.at_least_some_backend.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.hh.homework.at_least_some_backend.Utils;
import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertUserDto;
import ru.hh.homework.at_least_some_backend.dto.query.HHQueryUserDto;
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
                HHQueryUserDto.fromEntity(service.queryById(id))
        );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HHQueryUserDto> getAllOfType(@QueryParam("type") String typeStr) throws WebApplicationException
    {
        var type = Utils.requireEnum(HHUser.UserType.class, typeStr, "type");

        return service.queryAllByType(type)
                .stream()
                .map(HHQueryUserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertUser(HHInsertUserDto dto)
    {
        service.saveEntity(
                service.createEntity(dto)
        );
    }
}
