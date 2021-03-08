package ru.hh.school.exception;

import ru.hh.nab.starter.exceptions.WebApplicationExceptionMapper;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper extends WebApplicationExceptionMapper {

    @Override
    protected Response serializeException(Response.StatusType statusCode, WebApplicationException exception) {
        int status = exception.getResponse().getStatus();
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), status);
        return Response.status(status).entity(exceptionResponse).build();
    }
}
