package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.dao.CompanyDao;
import ru.hh.backend.dto.request.CompanyRequestDto;
import ru.hh.backend.dto.response.CompanyResponseDto;
import ru.hh.backend.mapper.CompanyMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/company")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class CompanyResource {

    private final CompanyMapper companyMapper;
    private final CompanyDao companyDao;

    public CompanyResource(CompanyMapper companyMapper, CompanyDao companyDao) {
        this.companyMapper = companyMapper;
        this.companyDao = companyDao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public CompanyResponseDto create(CompanyRequestDto companyRequestDto) {
        return companyMapper.map(companyDao.create(companyMapper.map(companyRequestDto)));
    }

    @GET
    public List<CompanyResponseDto> getAll() {
        return companyDao.getAll().stream().map(companyMapper::map).collect(Collectors.toList());
    }
}
