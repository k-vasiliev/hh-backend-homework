package ru.hh.school.resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.hibernate.NabSessionFactoryBean;
import ru.hh.school.config.ProdConfig;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Singleton
@Path("/")
public class ExampleResource {

  private final SessionFactory sessionFactory;

  @Inject
  public ExampleResource(SessionFactory sessionFactory) {
    this.sessionFactory =  sessionFactory;
  }

  private static final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

  @GET
  public void dummy() {
    logger.info("Do nothing");
  }


}
