import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.FavoriteVacancyDto;
import ru.hh.school.dto.SalaryDto;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.*;
import ru.hh.school.entity.comment.VacancyComment;
import ru.hh.school.entity.counter.VacancyCounter;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ContextConfiguration(classes = AppTestConfig.class)
public class VacancyResourceTest extends AppBaseTest {

    @Inject
    private VacancyDao vacancyDao;

    @Inject
    private EmployerDao employerDao;

    @Test
    public void postSingleFavoriteVacancyShouldSaveVacancyAndEmployerToDatabase() throws IOException {
        HttpResponse response = saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        assertEquals(200, response.statusCode());

        Vacancy vacancy = vacancyDao.getEager(vacancyId);
        VacancyDto vacancyDto = getVacancyDtoFromJson(SINGLE_VACANCY_JSON);

        assertVacancyDtoFromJsonAndEntityAreSame(vacancyDto, vacancy);
        assertEquals(0, (int) vacancy.getViewsCount().getCounter());
        assertEquals(DEFAULT_COMMENT, vacancy.getComment().getComment());
    }

    @Test
    public void postSingleFavoriteVacancyShouldSaveOnlyVacancyIfEmployerExists() throws IOException {
        Employer employer = createEmployerWithId(1);
        vacancyDao.save(employer);

        String vacancyJsonString = Files.readString(Path.of(JSON_BASE_PATH + SINGLE_VACANCY_JSON));
        doReturn(vacancyJsonString).when(apiService).fetchVacanciesFromApiById(vacancyId);
        doThrow(BadRequestException.class).when(apiService).fetchEmployersFromApiById(employerId);
        parametersMap.put("vacancy_id", String.valueOf(vacancyId));
        parametersMap.put("comment", DEFAULT_COMMENT);
        HttpResponse response = executePostRequestWithParams(FAVORITE_VACANCY_BASE_URL, parametersMap);
        assertEquals(200, response.statusCode());
        System.out.println("HERE");
        Vacancy vacancy = vacancyDao.getEager(vacancyId);
        Employer vacancyEmployer = vacancy.getEmployer();
        assertEquals(employer.getId(), vacancyEmployer.getId());
        assertEquals(employer.getName(), vacancyEmployer.getName());
        assertEquals(employer.getDescription(), vacancyEmployer.getDescription());
    }

    @Test
    public void postSameFavoriteVacancyTwiceShouldReturnBadRequestStatus() throws IOException {
        HttpResponse first = saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        assertEquals(200, first.statusCode());
        HttpResponse second = saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        assertEquals(400, second.statusCode());
    }

    @Test
    public void getFavoriteVacancyEndpointWithEmptyTableReturnsEmptyList() {
        Response response = executeGet(FAVORITE_VACANCY_BASE_URL);
        List<FavoriteVacancyDto> vacancies = response.readEntity(new GenericType<>() {});
        assertEquals(200, response.getStatus());
        assertEquals(0, vacancies.size());
    }

    @Test
    public void getFavoriteVacancyEndpointWithNonEmptyTableReturnsListOfValidDto() throws IOException {
        HttpResponse postResponse = saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        assertEquals(200, postResponse.statusCode());
        Response response = executeGet(FAVORITE_VACANCY_BASE_URL);
        assertEquals(200, response.getStatus());

        List<FavoriteVacancyDto> vacancies = response.readEntity(new GenericType<>() {});
        FavoriteVacancyDto vacancy = vacancies.get(0);
        VacancyDto jsonVacancy = getVacancyDtoFromJson(SINGLE_VACANCY_JSON);
        assertEquals(1, vacancies.size());

        assertVacancyDtoFromJsonAndDtoFromResponseAreSame(jsonVacancy, vacancy);
        assertEquals(1, vacancy.getViewsCount());
        assertEquals(DEFAULT_COMMENT, vacancy.getComment());
        assertEquals(Popularity.REGULAR, vacancy.getPopularity());
    }

    @Test
    public void getFavoriteEmployerEndpointPaginationWorks() throws IOException {
        saveMultipleVacanciesToDatabase(30);
        //Default parameters
        Response defaultParamsResponse = executeGet(FAVORITE_VACANCY_BASE_URL);
        List<FavoriteVacancyDto> vacancies = defaultParamsResponse.readEntity(new GenericType<>() {});
        assertEquals(200, defaultParamsResponse.getStatus());
        assertEquals(20, vacancies.size());
        assertEquals(1, vacancies.get(0).getId());
        assertEquals(20, vacancies.get(19).getId());
        //Per page = 25
        Response perPage25Response = executeGet(FAVORITE_VACANCY_BASE_URL + "?per_page=25");
        vacancies = perPage25Response.readEntity(new GenericType<>() {});
        assertEquals(200, perPage25Response.getStatus());
        assertEquals(25, vacancies.size());
        assertEquals(1, vacancies.get(0).getId());
        assertEquals(25, vacancies.get(24).getId());
        //Per page = 5
        Response perPage5Response = executeGet(FAVORITE_VACANCY_BASE_URL + "?per_page=5");
        vacancies = perPage5Response.readEntity(new GenericType<>() {});
        assertEquals(200, perPage5Response.getStatus());
        assertEquals(5, vacancies.size());
        assertEquals(1, vacancies.get(0).getId());
        assertEquals(5, vacancies.get(4).getId());
        //Page = 2
        Response page2Response = executeGet(FAVORITE_VACANCY_BASE_URL + "?page=1");
        vacancies = page2Response.readEntity(new GenericType<>() {});
        assertEquals(200, page2Response.getStatus());
        assertEquals(10, vacancies.size());
        assertEquals(21, vacancies.get(0).getId());
        assertEquals(30, vacancies.get(9).getId());
        //Page = 4 And Per Page = 5
        Response page4PerPage5Response = executeGet(FAVORITE_VACANCY_BASE_URL + "?page=4&per_page=5");
        vacancies = page4PerPage5Response.readEntity(new GenericType<>() {});
        assertEquals(200, page4PerPage5Response.getStatus());
        assertEquals(5, vacancies.size());
        assertEquals(21, vacancies.get(0).getId());
        assertEquals(25, vacancies.get(4).getId());
        //Page = 96 And Per Page = 21
        Response depthTestResponse = executeGet(FAVORITE_VACANCY_BASE_URL + "?page=96&per_page=21");
        assertEquals(400, depthTestResponse.getStatus());
    }

    @Test
    public void getFavoriteVacancyEndpointShouldIncrementVacancyAndEmployerCounterByOne() throws IOException {
        HttpResponse postResponse = saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        assertEquals(200, postResponse.statusCode());

        IntStream.range(0, 20).forEach(i -> executeGet(FAVORITE_VACANCY_BASE_URL));
        Response response = executeGet(FAVORITE_VACANCY_BASE_URL);
        List<FavoriteVacancyDto> vacancies = response.readEntity(new GenericType<>() {});
        FavoriteVacancyDto vacancy = vacancies.get(0);

        assertEquals(200, response.getStatus());
        assertEquals(1, vacancies.size());
        assertEquals(21, vacancy.getViewsCount());
        assertEquals(21, (int) employerDao.getEager(employerId).getViewsCount().getCounter());
    }

    @Test
    public void afterFileSettingsThresholdVacancyBecomesPopular() throws IOException {
        HttpResponse postResponse = saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        assertEquals(200, postResponse.statusCode());

        IntStream.range(0, 48).forEach(i -> executeGet(FAVORITE_VACANCY_BASE_URL));
        Response response = executeGet(FAVORITE_VACANCY_BASE_URL);
        List<FavoriteVacancyDto> vacancies = response.readEntity(new GenericType<>() {});
        FavoriteVacancyDto vacancy = vacancies.get(0);

        assertEquals(200, response.getStatus());
        assertEquals(49, vacancy.getViewsCount());
        assertEquals(Popularity.REGULAR, vacancy.getPopularity());

        response = executeGet(FAVORITE_VACANCY_BASE_URL);
        vacancies = response.readEntity(new GenericType<>() {});
        vacancy = vacancies.get(0);

        assertEquals(200, response.getStatus());
        assertEquals(50, vacancy.getViewsCount());
        assertEquals(Popularity.POPULAR, vacancy.getPopularity());
    }

    @Test
    public void counterIsThreadSafeForSingleEntry() throws IOException, InterruptedException {
        saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 200).forEach(i -> executor.execute(() -> executeGet(FAVORITE_VACANCY_BASE_URL)));
        awaitExecutorTermination(executor);

        Vacancy vacancy = vacancyDao.getEager(vacancyId);
        assertEquals(200, (int) vacancy.getViewsCount().getCounter());
        assertEquals(200, (int) vacancy.getEmployer().getViewsCount().getCounter());
    }

    @Test
    public void counterIsThreadSafeForMultipleEntries() throws IOException {
        saveMultipleVacanciesToDatabase(10);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        IntStream.range(0, 200).forEach(i -> executor.execute(() -> executeGet(FAVORITE_VACANCY_BASE_URL)));
        awaitExecutorTermination(executor);
        IntStream.range(1, 11)
                .mapToObj(i -> vacancyDao.getEager(i))
                .peek(vacancy -> logger.info(vacancy.getId() + " - " + vacancy.getName()))
                .peek(vacancy ->
                        assertEquals(
                                200, (int) vacancy.getEmployer().getViewsCount().getCounter()
                        ))
                .map(vacancy -> vacancy.getViewsCount().getCounter())
                .peek(counter -> logger.info("Views count: " + counter))
                .forEach(counter -> assertEquals(200, (int) counter));
    }


    @Test
    public void deleteVacancyWhileGettingFavorites() throws IOException, InterruptedException {
        saveMultipleVacanciesToDatabase(10);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 200; i++) {
            if (i == 100) {
                executor.execute(() -> executeDeleteRequest(FAVORITE_VACANCY_BASE_URL + "/" + 2));
            }
            executor.execute(() -> executeGet(FAVORITE_VACANCY_BASE_URL));
        }
        awaitExecutorTermination(executor);
        assertTrue(vacancyDao.get(Vacancy.class, 2).isEmpty());

        IntStream.range(1, 11).filter(i -> i != 2)
                .mapToObj(i -> vacancyDao.getEager(i))
                .peek(vacancy -> logger.info(vacancy.getId() + " - " + vacancy.getName()))
                .peek(vacancy ->
                        assertEquals(
                                200, (int) vacancy.getEmployer().getViewsCount().getCounter()
                        ))
                .map(vacancy -> vacancy.getViewsCount().getCounter())
                .peek(counter -> logger.info("Views count: " + counter))
                .forEach(counter -> assertEquals(200, (int) counter));
    }

    @Test
    public void deleteNonExistingFavoriteVacancyEndpointReturns404() {
        HttpResponse response = executeDeleteRequest(FAVORITE_VACANCY_BASE_URL + "/" + vacancyId);
        assertEquals(404, response.statusCode());
    }

    @Test
    public void deleteFavoriteVacancyEndpointShouldDeleteEmployerFromDatabase() throws IOException {
        exceptionRule.expect(NoResultException.class);
        HttpResponse postResponse = saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        assertEquals(200, postResponse.statusCode());

        Vacancy vacancy = vacancyDao.getEager(vacancyId);
        assertNotNull(vacancy);

        HttpResponse response = executeDeleteRequest(FAVORITE_VACANCY_BASE_URL + "/" + vacancyId);
        assertEquals(200, response.statusCode());
        vacancyDao.getEager(vacancyId);
    }

    @Test
    public void deleteFavoriteVacancyEndpointShouldDeleteVacancyAndCounterAndCommentButNotEmployer() throws IOException {
        HttpResponse postResponse = saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        assertEquals(200, postResponse.statusCode());

        Vacancy vacancy = vacancyDao.get(Vacancy.class, vacancyId).get();

        assertFalse(vacancyDao.get(VacancyComment.class, vacancyId).isEmpty());
        assertFalse(vacancyDao.get(VacancyCounter.class, vacancyId).isEmpty());
        assertNotNull(vacancy.getEmployer());

        HttpResponse response = executeDeleteRequest(FAVORITE_VACANCY_BASE_URL + "/" + vacancyId);
        assertEquals(200, response.statusCode());

        assertTrue(vacancyDao.get(VacancyComment.class, vacancyId).isEmpty());
        assertTrue(vacancyDao.get(VacancyCounter.class, vacancyId).isEmpty());
        assertNotNull(employerDao.get(Employer.class, vacancy.getEmployer().getId()));
    }

    @Test
    public void deleteFavoriteEmployerEndpointShouldDeleteVacancy() throws IOException {
        HttpResponse postResponse = saveSingleVacancyToDatabase(SINGLE_VACANCY_JSON);
        assertEquals(200, postResponse.statusCode());

        Vacancy vacancy = vacancyDao.get(Vacancy.class, vacancyId).get();

        assertFalse(vacancyDao.get(VacancyComment.class, vacancyId).isEmpty());
        assertFalse(vacancyDao.get(VacancyCounter.class, vacancyId).isEmpty());
        assertNotNull(vacancy);

        HttpResponse response = executeDeleteRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId);
        assertEquals(200, response.statusCode());

        assertTrue(vacancyDao.get(VacancyComment.class, vacancyId).isEmpty());
        assertTrue(vacancyDao.get(VacancyCounter.class, vacancyId).isEmpty());
        assertTrue(vacancyDao.get(Vacancy.class, vacancyId).isEmpty());
    }

    @Test
    public void refreshNonExistingFavoriteVacancyEndpointReturns404() {
        HttpResponse response = executePostRequest(FAVORITE_VACANCY_BASE_URL + "/" + vacancyId + "/refresh");
        assertEquals(404, response.statusCode());
    }

    @Test
    public void refreshFavoriteVacancyEndpointShouldUpdateVacancyFieldsInDatabase() throws IOException {
        String employerJsonString = Files.readString(Path.of(JSON_BASE_PATH + "/changed_employer.json"));
        String vacancyJsonString = Files.readString(Path.of(JSON_BASE_PATH + "/changed_vacancy.json"));
        doReturn(vacancyJsonString).when(apiService).fetchVacanciesFromApiById(vacancyId);
        doReturn(employerJsonString).when(apiService).fetchEmployersFromApiById(employerId);
        parametersMap.put("vacancy_id", String.valueOf(vacancyId));
        parametersMap.put("comment", DEFAULT_COMMENT);
        HttpResponse postResponse = executePostRequestWithParams(FAVORITE_VACANCY_BASE_URL, parametersMap);
        assertEquals(200, postResponse.statusCode());

        VacancyDto jsonVacancy = getVacancyDtoFromJson("/changed_vacancy.json");
        Vacancy vacancy = vacancyDao.getEager(vacancyId);
        assertVacancyDtoFromJsonAndEntityAreSame(jsonVacancy, vacancy);

        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + SINGLE_VACANCY_JSON));
        String actualEmployerJsonString = Files.readString(Paths.get(JSON_BASE_PATH + SINGLE_EMPLOYER_JSON));
        doReturn(jsonString).when(apiService).fetchVacanciesFromApiById(vacancyId);
        doReturn(actualEmployerJsonString).when(apiService).fetchEmployersFromApiById(employerId);
        HttpResponse response = executePostRequest(FAVORITE_VACANCY_BASE_URL + "/" + vacancyId + "/refresh");
        assertEquals(200, response.statusCode());

        vacancy = vacancyDao.getEager(vacancyId);
        jsonVacancy = getVacancyDtoFromJson(SINGLE_VACANCY_JSON);
        System.out.println(jsonVacancy + " - " + vacancy);
        assertVacancyDtoFromJsonAndEntityAreSame(jsonVacancy, vacancy);
    }

    @Test
    public void refreshVacancyWhileGettingFavorites() throws IOException {
        saveMultipleVacanciesToDatabase(10);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        String jsonString = Files.readString(Paths.get(JSON_BASE_PATH + SINGLE_VACANCY_JSON));
        String employerJsonString = Files.readString(Paths.get(JSON_BASE_PATH + SINGLE_EMPLOYER_JSON));
        VacancyDto jsonVacancy = getVacancyDtoFromJson(SINGLE_VACANCY_JSON);

        doReturn(jsonString).when(apiService).fetchVacanciesFromApiById(vacancyId);
        doReturn(employerJsonString).when(apiService).fetchEmployersFromApiById(employerId);

        for (int i = 0; i < 200; i++) {
            if (i == 100) {
                executor.execute(() -> executePostRequest(FAVORITE_VACANCY_BASE_URL + "/" + vacancyId + "/refresh"));
            }
            executor.execute(() -> executeGet(FAVORITE_VACANCY_BASE_URL));
        }
        awaitExecutorTermination(executor);

        Vacancy vacancyEntity = vacancyDao.getEager(vacancyId);
        assertVacancyDtoFromJsonAndEntityAreSame(jsonVacancy, vacancyEntity);
        assertEquals(200, (int) vacancyEntity.getViewsCount().getCounter());

        IntStream.range(1, 11)
                .mapToObj(i -> vacancyDao.getEager(i))
                .peek(vacancy -> logger.info(vacancy.getId() + " - " + vacancy.getName()))
                .map(vacancy -> vacancy.getViewsCount().getCounter())
                .peek(counter -> logger.info("Views count: " + counter))
                .forEach(counter -> assertEquals(200, (int) counter));
    }

    private HttpResponse saveSingleVacancyToDatabase(String pathToVacancyJson) throws IOException {
        String employerJsonString = Files.readString(Path.of(JSON_BASE_PATH + "/employer.json"));
        String vacancyJsonString = Files.readString(Path.of(JSON_BASE_PATH + pathToVacancyJson));
        doReturn(vacancyJsonString).when(apiService).fetchVacanciesFromApiById(vacancyId);
        doReturn(employerJsonString).when(apiService).fetchEmployersFromApiById(employerId);
        parametersMap.put("vacancy_id", String.valueOf(vacancyId));
        parametersMap.put("comment", DEFAULT_COMMENT);
        HttpResponse response = executePostRequestWithParams(FAVORITE_VACANCY_BASE_URL, parametersMap);
        parametersMap.clear();
        return response;
    }

    private void saveMultipleVacanciesToDatabase(Integer numOfVacancies) {
        Area area = new Area();
        area.setId(2);
        area.setName("Area name 2");
        for (int i = 1; i <= numOfVacancies; i++) {
            Vacancy vacancy = new Vacancy();
            Employer employer = createEmployerWithId(i);
            vacancyDao.save(employer);
            VacancyComment comment = new VacancyComment(DEFAULT_COMMENT);
            VacancyCounter counter = new VacancyCounter();
            comment.setVacancy(vacancy);
            counter.setVacancy(vacancy);
            vacancy.setId(i);
            vacancy.setName("Random name " + i);
            vacancy.setCreatedAt(OffsetDateTime.now());
            vacancy.setArea(area);
            vacancy.setComment(comment);
            vacancy.setViewsCount(counter);
            vacancy.setEmployer(employer);
            vacancyDao.save(vacancy);
        }
    }


    private void assertVacancyDtoFromJsonAndEntityAreSame(VacancyDto jsonDto, Vacancy entity) {
        Employer employer = entity.getEmployer();
        EmployerDto employerDto = jsonDto.getEmployer();
        assertEquals(employerDto.getId(), employer.getId());
        assertEquals(employerDto.getName(), employer.getName());

        Salary salary = entity.getSalary();
        SalaryDto salaryDto = jsonDto.getSalary();

        assertEquals(salaryDto.getTo(), salary.getTo());
        assertEquals(salaryDto.getFrom(), salary.getFrom());
        assertEquals(salaryDto.getCurrency(), salary.getCurrency());
        assertEquals(salaryDto.getGross(), salary.isGross());

        assertEquals(jsonDto.getId(), entity.getId());
        assertEquals(jsonDto.getName(), entity.getName());
        assertEquals(jsonDto.getCreatedAt(), entity.getCreatedAt());
        assertEquals(jsonDto.getArea().getId(), entity.getArea().getId());
        assertEquals(jsonDto.getArea().getName(), entity.getArea().getName());
    }

    private void assertVacancyDtoFromJsonAndDtoFromResponseAreSame(VacancyDto jsonDto, FavoriteVacancyDto responseDto) {
        assertEquals(jsonDto.getId(), responseDto.getId());
        assertEquals(jsonDto.getName(), responseDto.getName());
        assertEquals(jsonDto.getCreatedAt(), responseDto.getCreatedAt());

        SalaryDto responseSalary = responseDto.getSalary();
        SalaryDto jsonSalary = jsonDto.getSalary();

        assertEquals(jsonSalary.getTo(), responseSalary.getTo());
        assertEquals(jsonSalary.getFrom(), responseSalary.getFrom());
        assertEquals(jsonSalary.getCurrency(), responseSalary.getCurrency());
        assertEquals(jsonSalary.getGross(), responseSalary.getGross());

        assertEquals(jsonDto.getEmployer().getId(), responseDto.getEmployer().getId());
        assertEquals(jsonDto.getEmployer().getName(), responseDto.getEmployer().getName());

        assertEquals(jsonDto.getArea().getId(), responseDto.getArea().getId());
        assertEquals(jsonDto.getArea().getName(), responseDto.getArea().getName());
    }

}
