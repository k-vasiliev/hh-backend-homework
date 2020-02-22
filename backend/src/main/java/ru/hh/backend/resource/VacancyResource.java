package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.NegotiationDao;
import ru.hh.backend.dao.VacancyDao;
import ru.hh.backend.dto.request.VacancyRequestDto;
import ru.hh.backend.dto.response.NegotiationResponseDto;
import ru.hh.backend.dto.response.VacancyInfoResponseDto;
import ru.hh.backend.dto.response.VacancyResponseDto;
import ru.hh.backend.mapper.NegotiationMapper;
import ru.hh.backend.mapper.VacancyMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class VacancyResource {

    private final VacancyMapper vacancyMapper;
    private final NegotiationMapper negotiationMapper;
    private final VacancyDao vacancyDao;
    private final NegotiationDao negotiationDao;

    public VacancyResource(VacancyMapper vacancyMapper, VacancyDao vacancyDao, NegotiationDao negotiationDao,
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
    public VacancyInfoResponseDto getById(@PathParam("id") Long id) {
        return vacancyMapper.mapInfo(vacancyDao.get(id));
    }

    @GET
    @Path("/{id}/negotiations")
    public List<NegotiationResponseDto> geNegotiations(@PathParam("id") Long id) {
        return negotiationDao.getAllByVacancyId(id).stream().map(negotiationMapper::map).collect(Collectors.toList());
    }
}
