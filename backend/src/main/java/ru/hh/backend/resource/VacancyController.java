package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.NegotiationDao;
import ru.hh.backend.dao.VacancyDao;
import ru.hh.backend.dto.request.VacancyRequestDto;
import ru.hh.backend.dto.response.NegotiationResponseDto;
import ru.hh.backend.dto.response.VacancyResponseDto;
import ru.hh.backend.service.NegotiationMapper;
import ru.hh.backend.service.NegotiationService;
import ru.hh.backend.service.VacancyMapper;
import ru.hh.backend.service.VacancyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;


@Path("/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class VacancyController {
    private final VacancyMapper vacancyMapper;
    private final NegotiationMapper negotiationMapper;
    private final VacancyService vacancyService;
    private final NegotiationService negotiationService;

    public VacancyController(VacancyMapper vacancyMapper, VacancyService vacancyService, NegotiationService negotiationService,
                           NegotiationMapper negotiationMapper) {
        this.vacancyMapper = vacancyMapper;
        this.vacancyService = vacancyService;
        this.negotiationService = negotiationService;
        this.negotiationMapper = negotiationMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public VacancyResponseDto create(VacancyRequestDto vacancyRequestDto) {
        return vacancyMapper.map(vacancyService.create(vacancyMapper.map(vacancyRequestDto)));
    }

    @GET
    public List<VacancyResponseDto> getAll() {
        return vacancyService.getAll().stream().map(vacancyMapper::map).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public VacancyResponseDto getById(@PathParam("id") Long id) {
        return vacancyMapper.map(vacancyService.get(id));
    }

    @GET
    @Path("/{id}/negotiations")
    public List<NegotiationResponseDto> getNegotiations(@PathParam("id") Long id) {
        return negotiationService.getAllByVacancyId(id).stream().map(negotiationMapper::map).collect(Collectors.toList());
    }
}
