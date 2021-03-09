import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import feign.Feign;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import feign.mock.MockTarget;
import ru.hh.school.feignclient.HhApi;

public class HhApiTest {

    private HhApi api;

    private MockClient mockClient;

    private final String CORRECT_RESPONSE = "CORREC RESPONSE";

    @Before
    public void setup() {
        mockClient = new MockClient();
        api = Feign.builder()
                .client(mockClient
                        .ok(HttpMethod.GET, "/employers", CORRECT_RESPONSE)
                        .ok(HttpMethod.GET, "/employers?text=textVal&page=42&per_page=42", CORRECT_RESPONSE)
                        .ok(HttpMethod.GET, "/employers/42", CORRECT_RESPONSE)
                        .ok(HttpMethod.GET, "/vacancies", CORRECT_RESPONSE)
                        .ok(HttpMethod.GET, "/vacancies?text=textVal&page=42&per_page=42", CORRECT_RESPONSE)
                        .ok(HttpMethod.GET, "/vacancies/42", CORRECT_RESPONSE)
                        )
                .target(new MockTarget<>(HhApi.class));
    }

    @Test
    public void getEmployersWithoutParamsShouldBeOk() {
        assertEquals(CORRECT_RESPONSE, api.getEmployers(null, null, null));
    }

    @Test
    public void getEmployersWithParamsShouldBeOk() {
        assertEquals(CORRECT_RESPONSE, api.getEmployers("textVal", 42, 42));
    }

    @Test
    public void getEmployerShouldBeOk() {
        assertEquals(CORRECT_RESPONSE, api.getEmployer(42));
    }

    @Test
    public void getVacanciesWithoutParamsShouldBeOk() {
        assertEquals(CORRECT_RESPONSE, api.getVacancies(null, null, null));
    }

    @Test
    public void getVacanciesWithParamsShouldBeOk() {
        assertEquals(CORRECT_RESPONSE, api.getVacancies("textVal", 42, 42));
    }

    @Test
    public void getVacancyShouldBeOk() {
        assertEquals(CORRECT_RESPONSE, api.getVacancy(42));
    }
}
