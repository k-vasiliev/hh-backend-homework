package ru.hh.homework.at_least_some_backend.resource;

import ru.hh.homework.at_least_some_backend.dto.insert.HHInsertVacancyDto;
import ru.hh.homework.at_least_some_backend.dto.query.HHQueryNegotiationDto;
import ru.hh.homework.at_least_some_backend.dto.query.HHQueryVacancyBodyDto;
import ru.hh.homework.at_least_some_backend.dto.query.HHQueryVacancyDto;
import ru.hh.homework.at_least_some_backend.service.HHVacancyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Path("/vacancy")
public class HHVacancyResource
{
    @Inject
    private HHVacancyService service;
    @Inject
    private HHNegotiationResource negotiationResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HHQueryVacancyDto> getAllVacancies()
    {
        return service.queryAll()
                .stream()
                .map(HHQueryVacancyDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{vacancy_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HHQueryVacancyBodyDto getVacancyBody(@PathParam("vacancy_id") Long id)
    {
        return HHQueryVacancyBodyDto.fromEntity(
                service.queryById(id)
        );
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertNewVacancy(HHInsertVacancyDto dto)
    {
        service.saveEntity(
                service.createEntity(dto)
        );
    }

    /* Jersey не дает поставить путь "vacancy/{vacancy_id}/negotiations" в другой ресурс (игнорирует)
    поскольку "/vacancy" уже занят этим ресурсом (здесь). Но фронт хочет сюда.
    Два варианта: ресурс ходит в другой ресурс или в чужой сервис.
    Выбираю первый, поскольку
        1) разделение ответственности;
        2) инкапсуляция: логику обращения ресурса к сервису в случае изменений нужно будет поменять только там.
     */
    @GET
    @Path("{vacancy_id}/negotiations")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HHQueryNegotiationDto> getVacancyNegotiations(@PathParam("vacancy_id") Long vacancyId)
    {
        return negotiationResource.getNegotiationsForVacancy(vacancyId);
    }
}
