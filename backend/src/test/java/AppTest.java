import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = AppTestConfig.class)
public class AppTest extends NabTestBase {

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey().bindToRoot().build();
  }

  @Before
  public void before() {}

  // Не успел тесты написать =(
  @Test
  @Ignore
  public void createUserAndCompanyTest() {
    Response response = createRequest("/")
            .buildGet()
            .invoke();

    assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
  }
}
