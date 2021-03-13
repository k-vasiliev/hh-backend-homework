
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.school.dto.*;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AppTestConfig.class)
public class ApiIntegrationTest extends AppBaseTest {

    @Test
    public void getEmployerByIdEndpointReturnsValidDto() throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + "/employer.json"));
        when(apiService.fetchEmployersFromApiById(employerId)).thenReturn(jsonString);

        Response response = executeGetRequestWithParams(EMPLOYER_BASE_URL + "/" + employerId, "");
        assertEquals(200, response.getStatus());

        EmployerDtoById employer = response.readEntity(EmployerDtoById.class);
        EmployerDtoById jsonEmployer = getEmployerDtoFromJson("/employer.json");

        assertEquals(jsonEmployer.getId(), employer.getId());
        assertEquals(jsonEmployer.getName(), employer.getName());
        assertEquals(jsonEmployer.getDescription(), employer.getDescription());
        assertEquals(jsonEmployer.getArea().getId(), employer.getArea().getId());
        assertEquals(jsonEmployer.getArea().getName(), employer.getArea().getName());
    }

    @Test
    public void getEmployersEndpointReturnsValidDtoList() throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + "/employers.json"));
        when(apiService.fetchEmployersFromApi("", 0, 20)).thenReturn(jsonString);

        Response response = executeGetRequestWithParams(EMPLOYER_BASE_URL, "");
        assertEquals(200, response.getStatus());

        List<EmployerDto> employers = response.readEntity(new GenericType<>() {});
        assertEquals(20, employers.size());
        employers.stream().forEach( employerDto -> {
                    assertNotNull(employerDto.getId());
                    assertNotNull(employerDto.getName());
                }
        );

        EmployerDtoById jsonEmployer = getEmployerDtoFromJson("/employer.json");
        assertEquals(jsonEmployer.getId(), employers.get(0).getId());
        assertEquals(jsonEmployer.getName(), employers.get(0).getName());
    }

    @Test
    public void getVacancyByIdEndpointReturnsValidDto() throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + "/vacancy.json"));
        when(apiService.fetchVacanciesFromApiById(vacancyId)).thenReturn(jsonString);
        Response response = executeGetRequestWithParams(VACANCY_BASE_URL + "/" + vacancyId, "");
        VacancyDto vacancy = response.readEntity(VacancyDto.class);
        AreaDto areaDto = vacancy.getArea();
        SalaryDto salaryDto = vacancy.getSalary();
        assertEquals(200, response.getStatus());
        assertEquals(vacancyId, vacancy.getId());
        assertEquals("Junior Java / Kotlin разработчик", vacancy.getName());
        assertEquals(1, areaDto.getId());
        assertEquals("Москва", areaDto.getName());
        assertEquals(30000, (int) salaryDto.getFrom());
        assertEquals("RUR", salaryDto.getCurrency());
        assertNull(salaryDto.getTo());
        assertFalse(salaryDto.getGross());
    }

    @Test
    public void getVacanciesEndpointReturnsValidDtoList() throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + "/vacancies.json"));
        when(apiService.fetchVacanciesFromApi("", 0, 20)).thenReturn(jsonString);
        Response response = executeGetRequestWithParams(VACANCY_BASE_URL, "");
        List<VacancyDto> vacancies = response.readEntity(new GenericType<>() {});
        assertEquals(200, response.getStatus());
        assertEquals(20, vacancies.size());
        VacancyDto vacancy = vacancies.get(0);
        AreaDto areaDto = vacancy.getArea();
        SalaryDto salaryDto = vacancy.getSalary();
        assertEquals(200, response.getStatus());
        assertEquals(vacancyId, vacancy.getId());
        assertEquals("Junior Java / Kotlin разработчик", vacancy.getName());
        assertEquals(1, areaDto.getId());
        assertEquals("Москва", areaDto.getName());
        assertEquals(30000, (int) salaryDto.getFrom());
        assertEquals("RUR", salaryDto.getCurrency());
        assertNull(salaryDto.getTo());
        assertFalse(salaryDto.getGross());
    }

    @Test
    public void negativePageParameter() {
        exceptionRule.expect(BadRequestException.class);
        exceptionRule.expectMessage("page parameter can't be negative");
        when(apiService.fetchEmployersFromApi("headhunter", -1, 20)).thenCallRealMethod();
        executeGetRequestWithParams("/employer", "query=headhunter&page=-1");
    }

    @Test
    public void negativePerPageParameterThrowsBadRequestException() {
        exceptionRule.expect(BadRequestException.class);
        exceptionRule.expectMessage("per_page parameter can't be negative");
        when(apiService.fetchEmployersFromApi("headhunter", 0, -1)).thenCallRealMethod();
        executeGetRequestWithParams(EMPLOYER_BASE_URL, "query=headhunter&per_page=-1");
    }

    @Test
    public void greaterThen100PerPageParameterThrowsBadRequestException() {
        exceptionRule.expect(BadRequestException.class);
        exceptionRule.expectMessage("per_page parameter can't be greater then 100");
        when(apiService.fetchEmployersFromApi("headhunter", 0, 200)).thenCallRealMethod();
        executeGetRequestWithParams(EMPLOYER_BASE_URL, "query=headhunter&per_page=200");
    }

}
