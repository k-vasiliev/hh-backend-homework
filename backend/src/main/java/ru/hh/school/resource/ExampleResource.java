package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Singleton
@Path("/")
public class ExampleResource {

  private static final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

  @GET
  public void dummy() {
    logger.info("Do nothing");
  }
}
