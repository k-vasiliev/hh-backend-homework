package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.NegotiationDao;
import ru.hh.backend.dao.VacancyDao;
import ru.hh.backend.dto.request.VacancyRequestDto;
import ru.hh.backend.dto.response.NegotiationResponseDto;
import ru.hh.backend.dto.response.VacancyResponseDto;
import ru.hh.backend.service.NegotiationMapper;
import ru.hh.backend.service.VacancyMapper;

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
    private final VacancyDao vacancyDao;
    private final NegotiationDao negotiationDao;

    public VacancyController(VacancyMapper vacancyMapper, VacancyDao vacancyDao, NegotiationDao negotiationDao,
                           NegotiationMapper negotiationMapper) {
        this.vacancyMapper = vacancyMapper;
        this.vacancyDao = vacancyDao;
        this.negotiationDao = negotiationDao;
        this.negotiationMapper = negotiationMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public VacancyResponseDto create(VacancyRequestDto vacancyRequestDto) {
        return vacancyMapper.map(vacancyDao.create(vacancyMapper.map(vacancyRequestDto)));
    }

    @GET
    public List<VacancyResponseDto> getAll() {
        return vacancyDao.getAll().stream().map(vacancyMapper::map).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public VacancyResponseDto getById(@PathParam("id") Long id) {
        return vacancyMapper.map(vacancyDao.get(id));
    }

    @GET
    @Path("/{id}/negotiations")
    public List<NegotiationResponseDto> getNegotiations(@PathParam("id") Long id) {
        return negotiationDao.getAllByVacancyId(id).stream().map(negotiationMapper::map).collect(Collectors.toList());
    }
}
