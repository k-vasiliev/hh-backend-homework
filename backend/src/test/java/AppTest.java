import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.dto.UserDto;
import ru.hh.school.dto.request.CreateCompanyDto;
import ru.hh.school.entity.UserType;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = AppTestConfig.class)
public class AppTest extends NabTestBase {

    @Override
    protected NabApplication getApplication() {
        return NabApplication.builder().configureJersey().bindToRoot().build();
    }

    @Before
    public void before() {

    }

    @Test
    public void createUserAndCompanyTest() {
        UserDto userDto = new UserDto();
        userDto.setName("name");
        userDto.setType(UserType.employer);
        Response response = createRequest("/user")
            .buildPost(Entity.entity(userDto, MediaType.APPLICATION_JSON_TYPE))
            .invoke();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

        response = target("/user")
            .queryParam("type", "employer")
            .request()
            .get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("[{\"id\":1,\"name\":\"name\",\"type\":\"employer\"}]", response.readEntity(String.class));

        response = target("/user")
            .queryParam("type", "applicant")
            .request()
            .get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("[]", response.readEntity(String.class));

        CreateCompanyDto companyDto = new CreateCompanyDto();
        companyDto.setName("company");
        companyDto.setUserId(1);
        response = createRequest("/company")
            .buildPost(Entity.entity(companyDto, MediaType.APPLICATION_JSON_TYPE))
            .invoke();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

}
