import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.nab.testbase.postgres.embedded.EmbeddedPostgresDataSourceFactory;

import javax.ws.rs.core.Response;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = AppTestConfig.class)
public class AppTest extends NabTestBase {

  protected static EmbeddedPostgres pg;
  protected static SessionFactory sessionFactory;

  @BeforeClass
  public static void setUp() throws IOException, SQLException {
    pg = EmbeddedPostgresDataSourceFactory.getEmbeddedPostgres();
    StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .loadProperties("hibernate.test.properties").build();
    MetadataSources metadataSources = new MetadataSources(serviceRegistry);
    //ENTITY_CLASSES_REGISTRY.forEach(metadataSources::addAnnotatedClass);

    sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
  }

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey().bindToRoot().build();
  }

  @Before
  public void before() {}

  @Test
  public void createUserAndCompanyTest() {
    Response response = createRequest("/")
            .buildGet()
            .invoke();

    assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
  }
}
