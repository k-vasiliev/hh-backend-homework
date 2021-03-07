import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;

import javax.ws.rs.core.Response;

@ContextConfiguration(classes = AppTestConfig.class)
public class EmployerResourceTest extends NabTestBase {

    private final String BASE_URL = "/employer";

    @Override
    protected NabApplication getApplication() {
        return NabApplication.builder().configureJersey().bindToRoot().build();
    }

    private Response executeGetRequestWithParams(String url, String query) {
        return createRequest(url + "?" + query)
                .buildGet()
                .invoke();

    }

    @Test
    public void emptyParameters() {
        Response response = executeGetRequestWithParams(BASE_URL, "");

        System.out.println("RESPONSE!!!\n" + response.getEntity());

        //assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

}
