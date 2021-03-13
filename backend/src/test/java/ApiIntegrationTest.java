import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.VacancyDto;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AppTestConfig.class)
public class ApiIntegrationTest extends AppBaseTest {

    @Test
    public void getEmployerByIdEndpointReturnsValidDto() throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + SINGLE_EMPLOYER_JSON));
        doReturn(jsonString).when(apiService).fetchEmployersFromApiById(employerId);

        Response response = executeGetRequestWithParams(EMPLOYER_BASE_URL + "/" + employerId, "");
        assertEquals(200, response.getStatus());

        EmployerDtoById employer = response.readEntity(EmployerDtoById.class);
        EmployerDtoById jsonEmployer = getEmployerDtoFromJson(SINGLE_EMPLOYER_JSON);

        assertEquals(jsonEmployer.getId(), employer.getId());
        assertEquals(jsonEmployer.getName(), employer.getName());
        assertEquals(jsonEmployer.getDescription(), employer.getDescription());
        assertEquals(jsonEmployer.getArea().getId(), employer.getArea().getId());
        assertEquals(jsonEmployer.getArea().getName(), employer.getArea().getName());
    }

    @Test
    public void getEmployersEndpointReturnsValidDtoList() throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + MULTIPLE_EMPLOYERS_JSON));
        doReturn(jsonString).when(apiService).fetchEmployersFromApi("", 0, 20);

        Response response = executeGetRequestWithParams(EMPLOYER_BASE_URL, "");
        assertEquals(200, response.getStatus());

        List<EmployerDto> employers = response.readEntity(new GenericType<>() {});
        assertEquals(20, employers.size());
        employers.stream().forEach(
                employerDto -> {
                    assertNotNull(employerDto.getId());
                    assertNotNull(employerDto.getName());
                }
        );

        EmployerDtoById jsonEmployer = getEmployerDtoFromJson(SINGLE_EMPLOYER_JSON);
        assertEquals(jsonEmployer.getId(), employers.get(0).getId());
        assertEquals(jsonEmployer.getName(), employers.get(0).getName());
    }

    @Test
    public void getVacancyByIdEndpointReturnsValidDto() throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + SINGLE_VACANCY_JSON));
        doReturn(jsonString).when(apiService).fetchVacanciesFromApiById(vacancyId);
        Response response = executeGetRequestWithParams(VACANCY_BASE_URL + "/" + vacancyId, "");

        VacancyDto jsonVacancy = getVacancyDtoFromJson(SINGLE_VACANCY_JSON);
        VacancyDto vacancy = response.readEntity(VacancyDto.class);

        assertEquals(200, response.getStatus());
        assertEquals(jsonVacancy, vacancy);
    }

    @Test
    public void getVacanciesEndpointReturnsValidDtoList() throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + MULTIPLE_VACANCIES_JSON));
        doReturn(jsonString).when(apiService).fetchVacanciesFromApi("", 0, 20);

        Response response = executeGetRequestWithParams(VACANCY_BASE_URL, "");
        List<VacancyDto> vacancies = response.readEntity(new GenericType<>() {});
        assertEquals(200, response.getStatus());
        assertEquals(20, vacancies.size());

        VacancyDto jsonVacancy = getVacancyDtoFromJson(SINGLE_VACANCY_JSON);
        VacancyDto vacancy = vacancies.get(0);

        assertEquals(jsonVacancy.getArea(), vacancy.getArea());
        assertEquals(jsonVacancy.getSalary(), vacancy.getSalary());

        assertEquals(jsonVacancy.getId(), vacancy.getId());
        assertEquals(jsonVacancy.getName(), vacancy.getName());
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
