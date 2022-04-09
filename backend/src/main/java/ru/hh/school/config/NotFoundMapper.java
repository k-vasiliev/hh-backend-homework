package ru.hh.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import ru.hh.school.exception.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {
    @Autowired
    public Response toResponse(NotFoundException ex) {
        return Response.status(404).
                entity(ex.getMessage()).
                type("text/plain").
                build();
    }
}
