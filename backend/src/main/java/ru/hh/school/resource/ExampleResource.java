package ru.hh.school.resource;

import org.dom4j.datatype.DatatypeAttribute;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import ru.hh.school.config.ProdConfig;
import ru.hh.school.entity.Employer;

import javax.inject.Singleton;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Singleton
@Path("/")
@Import(ProdConfig.class)
public class ExampleResource {

  @Autowired
  ApplicationContext context;

  private static final Logger logger = LoggerFactory.getLogger(ExampleResource.class);

  @GET
  public void dummy() {
    DataSource dataSource = context.getBean(DataSource.class);
    Configuration configuration = new Configuration()
            .configure();
    SessionFactory sf = configuration.buildSessionFactory(
            new StandardServiceRegistryBuilder()
              .applySettings(configuration.getProperties())
              .applySetting(Environment.DATASOURCE, dataSource)
              .build()
    );
    Employer test = new Employer();
    test.setId(1);
    test.setName("Employer name");
    sf.getCurrentSession().save(test);
    logger.info("Do nothing");
  }
}
