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
import java.util.Collections;
import java.util.List;

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
        return companyService.getAllCompany();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseCompanyDTO getCompanyById(@PathParam("id") int id) {
        Company company = companyService.getCompanyById(id);
        return new ResponseCompanyDTO(company.getName(), company.getCompanyId());
    }

    @POST
    @Consumes(value = "application/json")
    public List<Company> createCompany(@Valid @RequestBody CreateCompanyDTO body) {
        List<Company> company = Collections.emptyList();
        try {
            company = Collections.singletonList(
                    companyService.createCompany(Integer.valueOf(body.getUserId()), body.getName())
            );
            logger.info(String.format(
                    "Create Company by CompanyName: %s, UserId: %s",
                    body.getName(), body.getUserId()
            ));
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
        }
        return company;
    }
}
