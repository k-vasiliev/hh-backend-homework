import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.nab.testbase.postgres.embedded.EmbeddedPostgresDataSourceFactory;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.resource.ExampleResource;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;


@ContextConfiguration(classes = AppTestConfig.class)
public class AppTest extends NabTestBase {

  private final static EmbeddedPostgres em = EmbeddedPostgresDataSourceFactory.getEmbeddedPostgres();

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey().bindToRoot().build();
  }

  @BeforeClass
  public static void before() throws IOException {
    Arrays.stream(Files.readString(Path.of("init.sql")).split(";"))
            .forEach(query -> executeQuery(query));
  }

  private static void executeQuery(String query) {
    try (Connection connection = em.getPostgresDatabase().getConnection();
          Statement statement = connection.createStatement()) {
        statement.executeUpdate(query);
      System.out.println("SUCCECC\n" + query);
    } catch (SQLException e) {
      System.out.println("SQLEXCEPTION" + e.getMessage());
    }
  }

  @Test
  public void createUserAndCompanyTest() {
    Response response = createRequest("/area")
            .buildGet()
            .invoke();

    System.out.println("RESPONSE!!!\n" + response);

    //assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
  }
}
