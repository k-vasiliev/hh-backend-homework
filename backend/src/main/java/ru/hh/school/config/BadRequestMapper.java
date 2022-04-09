package ru.hh.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import ru.hh.school.exception.BadRequestException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestMapper implements ExceptionMapper<BadRequestException> {
    @Autowired
    public Response toResponse(BadRequestException ex) {
        return Response.status(400).
                entity(ex.getMessage()).
                type("text/plain").
                build();
    }
}
