import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.*;
import ru.hh.school.util.VacancyMapper;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AppTestConfig.class)
public class VacancyResourceTest extends AppBaseTest {



    @Inject
    private VacancyDao vacancyDao;

    @Inject
    private EmployerDao employerDao;

    @Inject
    private VacancyMapper vacancyMapper;

    @Test
    public void postSingleFavoriteVacancyShouldSaveVacancyAndEmployerToDatabase() throws IOException {
        HttpResponse response = saveSingleVacancyToDatabase("/vacancy.json");
        assertEquals(200, response.statusCode());

        Vacancy vacancy = vacancyDao.getEager(employerId);
        EmployerDtoById jsonEmployer = getEmployerDtoFromJson("/employer.json");
        assertDtoFromJsonAndEntityAreSame(jsonEmployer, employer);
        assertEquals(0, (int) employer.getViewsCount().getCounter());
        assertEquals(DEFAULT_COMMENT, employer.getComment().getComment());
    }

    /*@Test
    public void postSameFavoriteEmployerTwiceShouldReturnBadRequestStatus() throws IOException {
        HttpResponse first = saveSingleEmployerToDatabase("/employer.json");
        assertEquals(200, first.statusCode());
        HttpResponse second = saveSingleEmployerToDatabase("/employer.json");
        assertEquals(400, second.statusCode());
    }*/

   /* @Test
    public void getFavoriteEmployerEndpointWithEmptyTableReturnsEmptyList() {
        Response response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        List<FavoriteEmployerDto> employers = response.readEntity(new GenericType<>() {
        });
        assertEquals(200, response.getStatus());
        assertEquals(0, employers.size());
    }*/

    /*@Test
    public void getFavoriteEmployerEndpointWithNonEmptyTableReturnsListOfValidDto() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase("/employer.json");
        assertEquals(200, postResponse.statusCode());
        Response response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        assertEquals(200, response.getStatus());

        List<FavoriteEmployerDto> employers = response.readEntity(new GenericType<>() {
        });
        FavoriteEmployerDto employer = employers.get(0);
        EmployerDtoById jsonEmployer = getEmployerDtoFromJson("/employer.json");
        assertEquals(1, employers.size());

        assertDtoFromJsonAndDtoFromResponseAreSame(jsonEmployer, employer);
        assertEquals(1, employer.getViewsCount());
        assertEquals(DEFAULT_COMMENT, employer.getComment());
        assertEquals(Popularity.REGULAR, employer.getPopularity());
    }*/

    /*@Test
    public void getFavoriteEmployerEndpointPaginationWorks() throws IOException {
        saveMultipleEmployersToDatabase(30);
        //Default parameters
        Response defaultParamsResponse = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        List<FavoriteEmployerDto> employers = defaultParamsResponse.readEntity(new GenericType<>() {
        });
        assertEquals(200, defaultParamsResponse.getStatus());
        assertEquals(20, employers.size());
        assertEquals(1, employers.get(0).getId());
        assertEquals(20, employers.get(19).getId());
        //Per page = 25
        Response perPage25Response = executeGet(FAVORITE_EMPLOYER_BASE_URL + "?per_page=25");
        employers = perPage25Response.readEntity(new GenericType<>() {
        });
        assertEquals(200, perPage25Response.getStatus());
        assertEquals(25, employers.size());
        assertEquals(1, employers.get(0).getId());
        assertEquals(25, employers.get(24).getId());
        //Per page = 5
        Response perPage5Response = executeGet(FAVORITE_EMPLOYER_BASE_URL + "?per_page=5");
        employers = perPage5Response.readEntity(new GenericType<>() {
        });
        assertEquals(200, perPage5Response.getStatus());
        assertEquals(5, employers.size());
        assertEquals(1, employers.get(0).getId());
        assertEquals(5, employers.get(4).getId());
        //Page = 2
        Response page2Response = executeGet(FAVORITE_EMPLOYER_BASE_URL + "?page=1");
        employers = page2Response.readEntity(new GenericType<>() {
        });
        assertEquals(200, page2Response.getStatus());
        assertEquals(10, employers.size());
        assertEquals(21, employers.get(0).getId());
        assertEquals(30, employers.get(9).getId());
        //Page = 4 And Per Page = 5
        Response page4PerPage5Response = executeGet(FAVORITE_EMPLOYER_BASE_URL + "?page=4&per_page=5");
        employers = page4PerPage5Response.readEntity(new GenericType<>() {
        });
        assertEquals(200, page4PerPage5Response.getStatus());
        assertEquals(5, employers.size());
        assertEquals(21, employers.get(0).getId());
        assertEquals(25, employers.get(4).getId());
        //Page = 96 And Per Page = 21
        Response depthTestResponse = executeGet(FAVORITE_EMPLOYER_BASE_URL + "?page=96&per_page=21");
        assertEquals(400, depthTestResponse.getStatus());
        System.out.println(depthTestResponse.readEntity(String.class));
    }*/

   /* @Test
    public void getFavoriteEmployerEndpointShouldIncrementCounterByOne() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase("/employer.json");
        assertEquals(200, postResponse.statusCode());

        IntStream.range(0, 20).forEach(i -> executeGet(FAVORITE_EMPLOYER_BASE_URL));
        Response response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        List<FavoriteEmployerDto> employers = response.readEntity(new GenericType<>() {
        });
        FavoriteEmployerDto employer = employers.get(0);

        assertEquals(200, response.getStatus());
        assertEquals(1, employers.size());
        assertEquals(21, employer.getViewsCount());
    }*/

    /*@Test
    public void afterFileSettingsThresholdEmployerBecomesPopular() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase("/employer.json");
        assertEquals(200, postResponse.statusCode());

        IntStream.range(0, 48).forEach(i -> executeGet(FAVORITE_EMPLOYER_BASE_URL));
        Response response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        List<FavoriteEmployerDto> employers = response.readEntity(new GenericType<>() {
        });
        FavoriteEmployerDto employer = employers.get(0);

        assertEquals(200, response.getStatus());
        assertEquals(49, employer.getViewsCount());
        assertEquals(Popularity.REGULAR, employer.getPopularity());

        response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        employers = response.readEntity(new GenericType<>() {
        });
        employer = employers.get(0);

        assertEquals(200, response.getStatus());
        assertEquals(50, employer.getViewsCount());
        assertEquals(Popularity.POPULAR, employer.getPopularity());
    }*/

   /* @Test
    public void counterIsThreadSafeForSingleEntry() throws IOException, InterruptedException {
        saveSingleEmployerToDatabase("/employer.json");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 200).forEach(i -> executor.execute(() -> executeGet(FAVORITE_EMPLOYER_BASE_URL)));
        awaitExecutorTermination(executor);

        Employer employer = employerDao.getEager(employerId);
        assertEquals(200, (int) employer.getViewsCount().getCounter());
    }*/

    /*@Test
    public void counterIsThreadSafeForMultipleEntries() throws IOException {
        saveMultipleEmployersToDatabase(10);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 200).forEach(i -> executor.execute(() -> executeGet(FAVORITE_EMPLOYER_BASE_URL)));
        awaitExecutorTermination(executor);
        IntStream.range(1, 11)
                .mapToObj(i -> employerDao.getEager(i))
                .peek(employer -> logger.info(employer.getId() + " - " + employer.getName()))
                .map(employer -> employer.getViewsCount().getCounter())
                .peek(counter -> logger.info("Views count: " + counter))
                .forEach(counter -> assertEquals(200, (int) counter));
    }*/


    /*@Test
    public void deleteEmployerWhileGettingFavorites() throws IOException, InterruptedException {
        saveMultipleEmployersToDatabase(10);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 200; i++) {
            if (i == 100) {
                executor.execute(() -> executeDeleteRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + 2));
            }
            executor.execute(() -> executeGet(FAVORITE_EMPLOYER_BASE_URL));
        }
        awaitExecutorTermination(executor);
        assertTrue(employerDao.get(Employer.class, 2).isEmpty());
        IntStream.range(1, 11).filter(i -> i != 2)
                .mapToObj(i -> employerDao.getEager(i))
                .peek(employer -> logger.info(employer.getId() + " - " + employer.getName()))
                .map(employer -> employer.getViewsCount().getCounter())
                .peek(counter -> logger.info("Views count: " + counter))
                .forEach(counter -> assertEquals(200, (int) counter));
    }*/

    /*@Test
    public void putNonExistingFavoriteEmployerEndpointReturns404() {
        parametersMap.put("comment", "different comment");
        HttpResponse response = executePutRequestWithParams(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId, parametersMap);
        assertEquals(404, response.statusCode());
    }*/

    /*@Test
    public void putFavoriteEmployerEndpointShouldUpdateEmployerCommentInDatabase() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase("/employer.json");
        assertEquals(200, postResponse.statusCode());

        Employer employer = employerDao.getEager(employerId);
        assertEquals(DEFAULT_COMMENT, employer.getComment().getComment());

        parametersMap.put("comment", "different comment");
        HttpResponse response = executePutRequestWithParams(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId, parametersMap);
        assertEquals(200, response.statusCode());

        Employer updatedEmployer = employerDao.getEager(employerId);
        assertEquals("different comment", updatedEmployer.getComment().getComment());
    }*/

    /*@Test
    public void deleteNonExistingFavoriteEmployerEndpointReturns404() {
        HttpResponse response = executeDeleteRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId);
        assertEquals(404, response.statusCode());
    }*/

    /*@Test
    public void deleteFavoriteEmployerEndpointShouldDeleteEmployerFromDatabase() throws IOException {
        exceptionRule.expect(NoResultException.class);
        HttpResponse postResponse = saveSingleEmployerToDatabase("/employer.json");
        assertEquals(200, postResponse.statusCode());

        Employer employer = employerDao.getEager(employerId);
        assertNotNull(employer);

        HttpResponse response = executeDeleteRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId);
        assertEquals(200, response.statusCode());
        employerDao.getEager(employerId);
    }*/

    /*@Test
    public void deleteFavoriteEmployerEndpointShouldDeleteEmployerCounterAndComment() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase("/employer.json");
        assertEquals(200, postResponse.statusCode());

        assertFalse(employerDao.get(EmployerComment.class, employerId).isEmpty());
        assertFalse(employerDao.get(EmployerCounter.class, employerId).isEmpty());

        HttpResponse response = executeDeleteRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId);
        assertEquals(200, response.statusCode());

        assertTrue(employerDao.get(EmployerComment.class, employerId).isEmpty());
        assertTrue(employerDao.get(EmployerCounter.class, employerId).isEmpty());
    }*/

    /*@Test
    public void refreshNonExistingFavoriteEmployerEndpointReturns404() {
        HttpResponse response = executePostRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId + "/refresh");
        assertEquals(404, response.statusCode());
    }*/

    /*@Test
    public void refreshFavoriteEmployerEndpointShouldUpdateEmployerFieldsInDatabase() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase("/changed_employer.json");
        assertEquals(200, postResponse.statusCode());

        EmployerDtoById jsonEmployer = getEmployerDtoFromJson("/changed_employer.json");
        Employer employer = employerDao.getEager(employerId);
        assertDtoFromJsonAndEntityAreSame(jsonEmployer, employer);

        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + "/employer.json"));
        when(apiService.fetchEmployersFromApiById(employerId)).thenReturn(jsonString);
        HttpResponse response = executePostRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId + "/refresh");
        assertEquals(200, response.statusCode());

        employer = employerDao.getEager(employerId);
        jsonEmployer = getEmployerDtoFromJson("/employer.json");
        assertDtoFromJsonAndEntityAreSame(jsonEmployer, employer);
    }*/

    /*@Test
    public void refreshEmployerWhileGettingFavorites() throws IOException {
        saveMultipleEmployersToDatabase(10);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        String jsonString = Files.readString(Paths.get(JSON_BASE_PATH + "/employer.json"));
        EmployerDtoById jsonEmployer = getEmployerDtoFromJson("/employer.json");
        when(apiService.fetchEmployersFromApiById(1)).thenReturn(jsonString);
        for (int i = 0; i < 200; i++) {
            if (i == 100) {
                executor.execute(() -> executePostRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + 1 + "/refresh"));
            }
            executor.execute(() -> executeGet(FAVORITE_EMPLOYER_BASE_URL));
        }
        awaitExecutorTermination(executor);
        Employer employerEntity = employerDao.getEager(1);
        assertDtoFromJsonAndEntityAreSame(jsonEmployer, employerEntity);
        assertEquals(200, (int) employerEntity.getViewsCount().getCounter());
        IntStream.range(1, 11)
                .mapToObj(i -> employerDao.getEager(i))
                .peek(employer -> logger.info(employer.getId() + " - " + employer.getName()))
                .map(employer -> employer.getViewsCount().getCounter())
                .peek(counter -> logger.info("Views count: " + counter))
                .forEach(counter -> assertEquals(200, (int) counter));
    }*/

    private HttpResponse saveSingleVacancyToDatabase(String pathToVacancyJson) throws IOException {
        String employerJsonString = Files.readString(Path.of(JSON_BASE_PATH + "/employer.json"));
        String vacancyJsonString = Files.readString(Path.of(JSON_BASE_PATH + pathToVacancyJson));
        when(apiService.fetchEmployersFromApiById(employerId)).thenReturn(employerJsonString);
        when(apiService.fetchVacanciesFromApiById(vacancyId)).thenReturn(vacancyJsonString);
        parametersMap.put("vacancy_id", String.valueOf(employerId));
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
            Employer employer = createAndSaveEmployerWithId(i);
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
            vacancyDao.save(vacancy);
        }
    }


    private void assertDtoFromJsonAndEntityAreSame(EmployerDtoById jsonDto, Employer entity) {
        assertEquals(jsonDto.getId(), entity.getId());
        assertEquals(jsonDto.getName(), entity.getName());
        assertEquals(jsonDto.getDescription(), entity.getDescription());
        assertEquals(jsonDto.getArea().getId(), entity.getArea().getId());
        assertEquals(jsonDto.getArea().getName(), entity.getArea().getName());
    }

    private void assertDtoFromJsonAndDtoFromResponseAreSame(EmployerDtoById jsonDto, FavoriteEmployerDto responseDto) {
        assertEquals(jsonDto.getId(), responseDto.getId());
        assertEquals(jsonDto.getName(), responseDto.getName());
        assertEquals(jsonDto.getDescription(), responseDto.getDescription());
        assertEquals(jsonDto.getArea().getId(), responseDto.getArea().getId());
        assertEquals(jsonDto.getArea().getName(), responseDto.getArea().getName());
    }

}
