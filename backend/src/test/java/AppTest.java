import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.nab.testbase.postgres.embedded.EmbeddedPostgresDataSourceFactory;

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

  @Test
  public void createUserAndCompanyTest() {
    Response response = createRequest("/area")
            .buildGet()
            .invoke();

    System.out.println("RESPONSE!!!\n" + response);

    //assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
  }
}
