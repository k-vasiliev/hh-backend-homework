package ru.hh.backend.resource;

import org.springframework.stereotype.Controller;
import ru.hh.backend.model.Company;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Path("/api/company")
@Controller
public class CompanyResource {

    @GET
    public Response getAll() {
        Company company = new Company();
        company.setId(1L);
        company.setName("hh");

        return Response.ok().entity(Collections.singletonList(company)).build();
    }
}
