package ru.hh.school.resource;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.hh.school.dto.EmployerDetailed;
import ru.hh.school.dto.Employers;
import ru.hh.school.service.HhService;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Singleton
@Path("/employer")
@Validated
public class EmployerResource {

    private final HhService hhService;

    public EmployerResource(HhService hhService) {
        this.hhService = hhService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployers(@QueryParam(value = "page") @Min(0) Integer page,
                                @QueryParam(value = "per_page") @Min(0) @Max(100) Integer perPage,
                                @QueryParam(value = "query") @NotBlank String query) {
        Employers employers = hhService.findEmployers(page, perPage, query);

        return Response.ok(employers).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployer(@PathParam("id") @NotNull Integer id) {
        EmployerDetailed employer = hhService.getEmployer(id);

        return Response.ok(employer).build();
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage()).append("\n");
        }
        return strBuilder.toString();
    }

}
