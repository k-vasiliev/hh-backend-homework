package ru.hh.school.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ru.hh.school.exception.FavoriteCollectionException;

public class FavoriteCollectionExceptionMapper
    implements ExceptionMapper<FavoriteCollectionException> {

  @Override
  public Response toResponse(FavoriteCollectionException exception) {
    return Response.status(exception.getSeggestedResponseCode(), exception.getMessage()).build();
  }
}
