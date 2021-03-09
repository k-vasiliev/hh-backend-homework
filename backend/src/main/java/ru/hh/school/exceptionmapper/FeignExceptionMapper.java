package ru.hh.school.exceptionmapper;

import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import feign.FeignException;

public class FeignExceptionMapper implements ExceptionMapper<FeignException> {

  @Override
  public Response toResponse(FeignException e) {
    String responseBody = e.responseBody().isPresent() ?
      StandardCharsets.ISO_8859_1.decode(e.responseBody().get()).toString() : null;
    return Response.status(e.status()).entity(responseBody).build();
  }
}
