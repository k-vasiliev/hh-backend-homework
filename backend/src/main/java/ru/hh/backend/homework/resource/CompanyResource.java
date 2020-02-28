package ru.hh.backend.homework.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.hh.backend.homework.dto.request.CompanyRequestDto;
import ru.hh.backend.homework.dto.response.CompanyResponseDto;
import ru.hh.backend.homework.entity.CompanyEntity;
import ru.hh.backend.homework.mapper.CompanyMapper;
import ru.hh.backend.homework.service.CompanyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/company")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class CompanyResource {
    private final CompanyMapper companyMapper;
    private final CompanyService companyService;

    @Autowired
    public CompanyResource(CompanyMapper companyMapper, CompanyService companyService) {
        this.companyMapper = companyMapper;
        this.companyService = companyService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public CompanyResponseDto create(CompanyRequestDto companyRequestDto) {
        CompanyEntity company = companyService.create(companyMapper.map(companyRequestDto));
        return companyMapper.map(company);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public List<CompanyResponseDto> getAll() {
        List<CompanyEntity> companies = companyService.getAll();
        return companies.stream()
                .map(companyMapper::map)
                .collect(Collectors.toList());
    }
}
