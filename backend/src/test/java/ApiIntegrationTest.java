
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.service.ApiService;


import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AppTestConfig.class, ApiMockConfig.class})
public class EmployerResourceTest extends NabTestBase {


    @Inject
    private ApiService apiService;
    @Inject
    private EmployerResource employerResource;

    @Override
    protected NabApplication getApplication() {
        return NabApplication.builder().configureJersey().bindToRoot().build();
    }

    private Response executeGetRequestWithParams(String url, String query) {
        return createRequest(url + "?" + query)
                .buildGet()
                .invoke();
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void getEmployerByIdEndpointReturnsValidDto() throws IOException {
        String jsonString = Files.readString(Path.of("src/test/resources/employer.json"));
        when(apiService.fetchEmployersFromApiById(1)).thenReturn(jsonString);
        Response response = employerResource.getEmployerFromApiById(1);
        EmployerDtoById employer = (EmployerDtoById) response.getEntity();
        AreaDto areaDto = employer.getArea();
        assertEquals(200, response.getStatus());
        assertEquals(1, employer.getId());
        assertEquals("NCI", employer.getName());
        assertEquals("Random description", employer.getDescription());
        assertEquals(1, areaDto.getId());
        assertEquals("Москва", areaDto.getName());
    }

    @Test
    public void getEmployersEndpointReturnsValidDtoList() throws IOException {
        String jsonString = Files.readString(Path.of("src/test/resources/employers.json"));
        when(apiService.fetchEmployersFromApi("", 0, 20)).thenReturn(jsonString);
        Response response = employerResource.getEmployersFromApi("", 0, 20);
        List<EmployerDto> employers = (List<EmployerDto>) response.getEntity();
        assertEquals(200, response.getStatus());
        assertEquals(20, employers.size());
        employers.stream().forEach( employerDto -> {
                    assertNotNull(employerDto.getId());
                    assertNotNull(employerDto.getName());
                }
        );
        assertEquals(4849846, employers.get(0).getId());
        assertEquals("𝐁𝐥𝐚𝐜𝐤 𝐖𝐨𝐨𝐝", employers.get(0).getName());
    }

    @Test
    public void negativePageParameter() {
        exceptionRule.expect(BadRequestException.class);
        exceptionRule.expectMessage("page parameter can't be negative");
        when(apiService.fetchEmployersFromApi("headhunter", -1, 20)).thenCallRealMethod();
        executeGetRequestWithParams("/employer", "query=headhunter&page=-1");
    }



}
