import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.exception.InvalidPaginationException;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

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
        List<EmployerDto> employers = response.readEntity(new GenericType<>() {
        });
        assertEquals(200, response.getStatus());
        assertEquals(20, employers.size());
    }

    @Test
    public void queryParameter() {
        Response response = executeGetRequestWithParams(BASE_URL, "query=headhunter");
        List<EmployerDto> employers = response.readEntity(new GenericType<>() {
        });
        assertEquals(200, response.getStatus());
    }

    @Test
    public void invalidPageParameter() {
        Response response = executeGetRequestWithParams(BASE_URL, "query=headhunter&page=-1");
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

}
