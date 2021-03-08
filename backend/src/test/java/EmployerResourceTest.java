import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.dto.EmployerDto;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
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
    public void emptyParametersShouldReturnListWithDefaultPaginationParameters() {
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
    public void negativePageParameter() {
        Response response = executeGetRequestWithParams(BASE_URL, "query=headhunter&page=-1");
        assertEquals(400, response.getStatus());
        assertEquals("page parameter can't be negative", response.readEntity(String.class));
    }

    @Test
    public void nonIntegerPageParameter() {
        Response response = executeGetRequestWithParams(BASE_URL, "query=headhunter&page=a");
        assertEquals(400, response.getStatus());
        assertEquals("unable to parse page parameter", response.readEntity(String.class));
    }

    @Test
    public void negativePerPageParameter() {
        Response response = executeGetRequestWithParams(BASE_URL, "query=headhunter&per_page=-1");
        assertEquals(400, response.getStatus());
        assertEquals("per_page parameter can't be negative", response.readEntity(String.class));
    }

    @Test
    public void greaterThen100PerPageParameter() {
        Response response = executeGetRequestWithParams(BASE_URL, "query=headhunter&per_page=200");
        assertEquals(400, response.getStatus());
        assertEquals("per_page parameter can't be greater then 100", response.readEntity(String.class));
    }

    @Test
    public void nonIntegerPerPageParameter() {
        Response response = executeGetRequestWithParams(BASE_URL, "query=headhunter&per_page=a");
        assertEquals(400, response.getStatus());
        assertEquals("unable to parse per_page parameter", response.readEntity(String.class));
    }

    @Test
    public void invalidPerPageAndPageParameters() {
        Response response = executeGetRequestWithParams(BASE_URL, "query=headhunter&page=a&per_page=a");
        assertEquals(400, response.getStatus());
        assertEquals("unable to parse page parameter", response.readEntity(String.class));
    }

    @Test
    public void validPerPageParameterShouldReturnListOfSpecifiedLength() {
        Response response = executeGetRequestWithParams(BASE_URL, "per_page=10");
        List<EmployerDto> employers = response.readEntity(new GenericType<>() {
        });
        assertEquals(200, response.getStatus());
        assertEquals(10, employers.size());
    }

}
