import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.EmployerDao;
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
import java.util.Optional;

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

    private HttpResponse saveSingleEmployerToDatabase() throws IOException {
        String jsonString = Files.readString(Path.of("src/test/resources/employer.json"));
        when(apiService.fetchEmployersFromApiById(employerId)).thenReturn(jsonString);
        parametersMap.put("employer_id", String.valueOf(employerId));
        parametersMap.put("comment", "New comment");
        HttpResponse response =  executePostRequestWithParams(FAVORITE_EMPLOYER_BASE_URL, parametersMap);
        parametersMap.clear();
        return response;
    }

    @Test
    public void postSingleFavoriteEmployerShouldSaveEntityToDatabase() throws IOException {
        HttpResponse response = saveSingleEmployerToDatabase();
        assertEquals(200, response.statusCode());
        Employer employer = employerDao.getEager(employerId);
        assertEquals(employerId, employer.getId());
        assertEquals("𝐁𝐥𝐚𝐜𝐤 𝐖𝐨𝐨𝐝", employer.getName());
        assertEquals("Random description", employer.getDescription());
        assertEquals(0, (int) employer.getViewsCount().getCounter());
        assertEquals(1381, employer.getArea().getId());
        assertEquals("Сургут", employer.getArea().getName());
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
        assertEquals("𝐁𝐥𝐚𝐜𝐤 𝐖𝐨𝐨𝐝", employer.getName());
        assertEquals("Random description", employer.getDescription());
        assertEquals(1, employer.getViewsCount());
        assertEquals(1381, employer.getArea().getId());
        assertEquals("Сургут", employer.getArea().getName());
        assertEquals("New comment", employer.getComment());
        assertEquals(Popularity.REGULAR, employer.getPopularity());
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
        assertEquals("𝐁𝐥𝐚𝐜𝐤 𝐖𝐨𝐨𝐝", employer.getName());
        assertEquals("Random description", employer.getDescription());
        assertEquals(1381, employer.getArea().getId());
        assertEquals("Сургут", employer.getArea().getName());
    }


}
