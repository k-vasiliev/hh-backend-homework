package ru.hh.school.jackson;

import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

  @Inject
  private ObjectMapper mapper;

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return mapper;
  }
}
