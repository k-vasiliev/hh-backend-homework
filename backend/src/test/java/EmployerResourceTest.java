import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Popularity;
import ru.hh.school.service.ApiService;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AppTestConfig.class, ApiMockConfig.class})
@Transactional
public class EmployerResourceTest extends AppBaseTest {

    private final Map<String, String> parametersMap = new HashMap<>();

    @Inject
    private ApiService apiService;

    @Inject
    private EmployerDao employerDao;

    @Inject
    private FileSettings fileSettings;

    @Before
    public void init() throws IOException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        String truncateQuery =
                Files.readString(Path.of("src/test/resources/truncate.sql"));
        List.of(truncateQuery.split(";")).stream()
                .forEach(query -> session.createNativeQuery(query).executeUpdate());
        parametersMap.clear();
        transaction.commit();
        session.close();
    }

    @Test
    public void postSingleFavoriteEmployerShouldSaveEntityToDatabase() throws IOException {
        HttpResponse response = saveSingleEmployerToDatabase();
        assertEquals(200, response.statusCode());
        Employer employer = employerDao.getEager(employerId);
        assertEquals(employerId, employer.getId());
        assertEquals("Black Wood", employer.getName());
        assertEquals("Random description", employer.getDescription());
        assertEquals(0, (int) employer.getViewsCount().getCounter());
        assertEquals(1381, employer.getArea().getId());
        assertEquals("Black", employer.getArea().getName());
        assertEquals("New comment", employer.getComment().getComment());
    }

    @Test
    public void postSameFavoriteEmployerTwiceShouldReturnBadRequestStatus() throws IOException {
        HttpResponse first = saveSingleEmployerToDatabase();
        assertEquals(200, first.statusCode());
        HttpResponse second = saveSingleEmployerToDatabase();
        assertEquals(400, second.statusCode());
    }

    @Test
    public void getFavoriteEmployerEndpointWithEmptyTableReturnsEmptyList() {
        Response response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        List<FavoriteEmployerDto> employers = response.readEntity(new GenericType<>(){});
        assertEquals(200, response.getStatus());
        assertEquals(0, employers.size());
    }

    @Test
    public void getFavoriteEmployerEndpointWithNonEmptyTableReturnsListOfValidDto() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase();
        assertEquals(200, postResponse.statusCode());
        Response response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        List<FavoriteEmployerDto> employers = response.readEntity(new GenericType<>(){});
        assertEquals(200, response.getStatus());
        assertEquals(1, employers.size());
        FavoriteEmployerDto employer = employers.get(0);
        assertEquals(employerId, employer.getId());
        assertEquals("Black Wood", employer.getName());
        assertEquals("Random description", employer.getDescription());
        assertEquals(1, employer.getViewsCount());
        assertEquals(1381, employer.getArea().getId());
        assertEquals("Black", employer.getArea().getName());
        assertEquals("New comment", employer.getComment());
        assertEquals(Popularity.REGULAR, employer.getPopularity());
    }

    @Test
    public void getFavoriteEmployerEndpointPaginationWorks() throws IOException {
        saveMultipleEmployersToDatabase(30);
        //Default parameters
        Response defaultParamsResponse = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        List<FavoriteEmployerDto> employers = defaultParamsResponse.readEntity(new GenericType<>(){});
        assertEquals(200, defaultParamsResponse.getStatus());
        assertEquals(20, employers.size());
        assertEquals(1, employers.get(0).getId());
        assertEquals(20, employers.get(19).getId());
        //Per page = 25
        Response perPage25Response = executeGet(FAVORITE_EMPLOYER_BASE_URL + "?per_page=25");
        employers = perPage25Response.readEntity(new GenericType<>(){});
        assertEquals(200, perPage25Response.getStatus());
        assertEquals(25, employers.size());
        assertEquals(1, employers.get(0).getId());
        assertEquals(25, employers.get(24).getId());
        //Per page = 5
        Response perPage5Response = executeGet(FAVORITE_EMPLOYER_BASE_URL + "?per_page=5");
        employers = perPage5Response.readEntity(new GenericType<>(){});
        assertEquals(200, perPage5Response.getStatus());
        assertEquals(5, employers.size());
        assertEquals(1, employers.get(0).getId());
        assertEquals(5, employers.get(4).getId());
        //Page = 2
        Response page2Response = executeGet(FAVORITE_EMPLOYER_BASE_URL + "?page=1");
        employers = page2Response.readEntity(new GenericType<>(){});
        assertEquals(200, page2Response.getStatus());
        assertEquals(10, employers.size());
        assertEquals(21, employers.get(0).getId());
        assertEquals(30, employers.get(9).getId());
        //Page = 4 And Per Page = 5
        Response page4PerPage5Response = executeGet(FAVORITE_EMPLOYER_BASE_URL + "?page=4&per_page=5");
        employers = page4PerPage5Response.readEntity(new GenericType<>(){});
        assertEquals(200, page4PerPage5Response.getStatus());
        assertEquals(5, employers.size());
        assertEquals(21, employers.get(0).getId());
        assertEquals(25, employers.get(4).getId());
    }

    @Test
    public void getFavoriteEmployerEndpointShouldIncrementCounterByOne() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase();
        assertEquals(200, postResponse.statusCode());
        Response response = null;
        for (int i = 0; i < 20; i ++) {
            response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        }
        List<FavoriteEmployerDto> employers = response.readEntity(new GenericType<>(){});
        assertEquals(200, response.getStatus());
        assertEquals(1, employers.size());
        FavoriteEmployerDto employer = employers.get(0);
        assertEquals(20, employer.getViewsCount());
    }

    @Test
    public void afterCertainThresholdEmployerBecomesPopular() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase();
        assertEquals(200, postResponse.statusCode());
        Response response = null;
        for (int i = 0; i < fileSettings.getInteger("popularity.settings") - 1; i ++) {
            response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        }
        List<FavoriteEmployerDto> employers = response.readEntity(new GenericType<>(){});
        assertEquals(200, response.getStatus());
        FavoriteEmployerDto employer = employers.get(0);
        assertEquals(49, employer.getViewsCount());
        assertEquals(Popularity.REGULAR, employer.getPopularity());

        response = executeGet(FAVORITE_EMPLOYER_BASE_URL);
        employers = response.readEntity(new GenericType<>(){});
        assertEquals(200, response.getStatus());
        employer = employers.get(0);
        assertEquals(50, employer.getViewsCount());
        assertEquals(Popularity.POPULAR, employer.getPopularity());
    }

    @Test
    public void counterIsThreadSafe() throws IOException, InterruptedException {
        saveSingleEmployerToDatabase();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 1000; i++) {
            threadPool.submit(() -> executeGet(FAVORITE_EMPLOYER_BASE_URL));
        }
        threadPool.awaitTermination(12000, TimeUnit.MILLISECONDS);
        Employer employer = employerDao.getEager(employerId);
        System.out.println(employer.getViewsCount().getCounter());
        Thread.sleep(1000);
        employer = employerDao.getEager(employerId);
        System.out.println(employer.getViewsCount().getCounter());
        assertEquals(1000, (int) employer.getViewsCount().getCounter());
    }

    @Test
    public void putFavoriteEmployerEndpointShouldUpdateEmployerCommentInDatabase() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase();
        assertEquals(200, postResponse.statusCode());
        Employer employer = employerDao.getEager(employerId);
        assertEquals("New comment", employer.getComment().getComment());
        parametersMap.put("comment", "different comment");
        HttpResponse response = executePutRequestWithParams(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId, parametersMap);
        assertEquals(200, response.statusCode());
        Employer updatedEmployer = employerDao.getEager(employerId);
        assertEquals("different comment", updatedEmployer.getComment().getComment());
    }

    @Test
    public void deleteFavoriteEmployerEndpointShouldDeleteEmployerFromDatabase() throws IOException {
        exceptionRule.expect(NoResultException.class);
        HttpResponse postResponse = saveSingleEmployerToDatabase();
        assertEquals(200, postResponse.statusCode());
        Employer employer = employerDao.getEager(employerId);
        assertNotNull(employer);
        HttpResponse response = executeDeleteRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId);
        assertEquals(200, response.statusCode());
        employerDao.getEager(employerId);
    }

    @Test
    public void refreshFavoriteEmployerEndpointShouldUpdateEmployerFieldsInDatabase() throws IOException {
        HttpResponse postResponse = saveSingleEmployerToDatabase();
        assertEquals(200, postResponse.statusCode());
        Employer employer = employerDao.getEager(employerId);
        employer.setName("Some name");
        employer.setDescription("Some description");
        Area area = new Area();
        area.setId(1);
        area.setName("Москва");
        employer.setArea(area);
        employerDao.update(employer);
        employer = employerDao.getEager(employerId);
        assertEquals("Some name", employer.getName());
        assertEquals("Some description", employer.getDescription());
        assertEquals(1, employer.getArea().getId());
        assertEquals("Москва", employer.getArea().getName());
        HttpResponse response = executePostRequest(FAVORITE_EMPLOYER_BASE_URL + "/" + employerId + "/refresh");
        assertEquals(200, response.statusCode());
        employer = employerDao.getEager(employerId);
        assertEquals("Black Wood", employer.getName());
        assertEquals("Random description", employer.getDescription());
        assertEquals(1381, employer.getArea().getId());
        assertEquals("Black", employer.getArea().getName());
    }

    private HttpResponse saveSingleEmployerToDatabase() throws IOException {
        String jsonString = Files.readString(Path.of("src/test/resources/employer.json"));
        when(apiService.fetchEmployersFromApiById(employerId)).thenReturn(jsonString);
        parametersMap.put("employer_id", String.valueOf(employerId));
        parametersMap.put("comment", "New comment");
        HttpResponse response =  executePostRequestWithParams(FAVORITE_EMPLOYER_BASE_URL, parametersMap);
        parametersMap.clear();
        return response;
    }

    private void saveMultipleEmployersToDatabase(Integer numOfEmployers) throws IOException {
        String jsonString = Files.readString(Path.of("src/test/resources/employer.json"));
        for (int i = 1; i <= numOfEmployers; i++) {
            String employerJson = jsonString
                    .replace("4849846", String.valueOf(i))
                    .replace("Black Wood", "Black Wood " + i);
            when(apiService.fetchEmployersFromApiById(employerId)).thenReturn(employerJson);
            parametersMap.put("employer_id", String.valueOf(employerId));
            parametersMap.put("comment", "New comment");
            executePostRequestWithParams(FAVORITE_EMPLOYER_BASE_URL, parametersMap);
            parametersMap.clear();
        }
    }

}
