package ru.hh.nab.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hh.nab.dto.CreateCompanyDTO;
import ru.hh.nab.dto.ResponseCompanyDTO;
import ru.hh.nab.entity.Company;
import ru.hh.nab.service.CompanyService;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/company")
@Singleton
public class CompanyResource {

    private static Logger logger = LoggerFactory.getLogger(CompanyResource.class);

    private final CompanyService companyService;

    public CompanyResource(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ResponseCompanyDTO> getAllCompany() {
        return companyService.getAllCompany().stream()
                .map(company -> new ResponseCompanyDTO(company).build())
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(value = "application/json")
    public Company createCompany(@Valid @RequestBody CreateCompanyDTO body) {
        logger.info("Create Company");
        return companyService.createCompany(Integer.valueOf(body.getUserId()), body.getName());
    }
}
