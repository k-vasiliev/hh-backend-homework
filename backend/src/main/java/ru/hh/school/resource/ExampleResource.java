package ru.hh.school.resource;

import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Import;
import ru.hh.school.config.ProdConfig;
import ru.hh.school.entity.Employer;

import javax.inject.Inject;
import javax.inject.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Singleton
@Path("/")
@Import(ProdConfig.class)
public class ExampleResource {

  private final SessionFactory sessionFactory;

  @Inject
  public ExampleResource(SessionFactory sessionFactory) {
    this.sessionFactory =  sessionFactory;
  }

  private static final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

  @GET
  public void dummy() {
    Employer test = new Employer();
    test.setId(1);
    test.setName("Employer name");
    logger.info("Do nothing");
  }
}
