import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.comment.EmployerComment;
import ru.hh.school.entity.counter.EmployerCounter;
import ru.hh.school.service.ApiService;
import ru.hh.school.component.EmployerMapper;
import ru.hh.school.component.VacancyMapper;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public abstract class AppBaseTest extends NabTestBase {

    protected final String EMPLOYER_BASE_URL = "/employer";
    protected final String VACANCY_BASE_URL = "/vacancy";
    protected final String FAVORITE_EMPLOYER_BASE_URL = "/favorites/employer";
    protected final String FAVORITE_VACANCY_BASE_URL = "/favorites/vacancy";

    protected final String JSON_BASE_PATH = "src/test/resources/json";
    protected final String DEFAULT_COMMENT = "DEFAULT COMMENT";
    protected final int vacancyId = 1;
    protected final int employerId = 1;
    protected final Map<String, String> parametersMap = new HashMap<>();

    protected String SINGLE_EMPLOYER_JSON = "/employer.json";
    protected String MULTIPLE_EMPLOYERS_JSON = "/employers.json";
    protected String SINGLE_VACANCY_JSON = "/vacancy.json";
    protected String MULTIPLE_VACANCIES_JSON = "/vacancies.json";

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Inject
    protected SessionFactory sessionFactory;
    @Inject
    protected FileSettings fileSettings;
    @Inject
    protected EmployerMapper employerMapper;
    @Inject
    protected VacancyMapper vacancyMapper;
    @Inject
    protected ApiService apiService;

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

    @Override
    protected NabApplication getApplication() {
        return NabApplication.builder().configureJersey().bindToRoot().build();
    }

    protected HttpResponse<String> executePostRequestWithParams(String url, Map<String, String> params) {
        HttpRequest request = createPostRequestWithParameters(url, params);
        return executeRequest(request);
    }

    protected HttpResponse<String> executePutRequestWithParams(String url, Map<String, String> params) {
        HttpRequest request = createPutRequestWithParameters(url, params);
        return executeRequest(request);
    }

    protected HttpResponse<String> executeDeleteRequest(String url) {
        HttpRequest request = createDeleteRequest(url);
        return executeRequest(request);
    }

    protected HttpResponse<String> executePostRequest(String url) {
        HttpRequest request = createPostRequest(url);
        return executeRequest(request);
    }

    protected Response executeGetRequestWithParams(String url, String query) {
        return createRequest(url + "?" + query)
                .buildGet()
                .invoke();
    }

    private HttpRequest createPostRequest(String url) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(baseUrl() + url))
                    .POST(HttpRequest.BodyPublishers.noBody()).build();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private HttpRequest createDeleteRequest(String url) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(baseUrl() + url))
                    .DELETE().build();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private HttpRequest createPostRequestWithParameters(String url, Map<String, String> params) {
        try {
            String requestParams = params.keySet().stream()
                    .map(key -> key + "=" + params.get(key))
                    .collect(Collectors.joining("&"));
            return HttpRequest.newBuilder()
                    .uri(new URI(baseUrl() + url))
                    .headers("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestParams))
                    .build();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private HttpRequest createPutRequestWithParameters(String url, Map<String, String> params) {
        try {
            String requestParams = params.keySet().stream()
                    .map(key -> key + "=" + params.get(key))
                    .collect(Collectors.joining("&"));
            return HttpRequest.newBuilder()
                    .uri(new URI(baseUrl() + url))
                    .headers("Content-Type", "application/x-www-form-urlencoded")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestParams))
                    .build();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private HttpResponse<String> executeRequest(HttpRequest request) {
        try {
            return HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    protected void awaitExecutorTermination(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    protected Employer createEmployerWithId(Integer id) {
        Area area = new Area();
        area.setId(1);
        area.setName("Area name");
        Employer employer = new Employer();
        EmployerComment comment = new EmployerComment(DEFAULT_COMMENT);
        EmployerCounter counter = new EmployerCounter();
        comment.setEmployer(employer);
        counter.setEmployer(employer);
        employer.setId(id);
        employer.setName("Random name " + id);
        employer.setDescription("Random description");
        employer.setArea(area);
        employer.setComment(comment);
        employer.setViewsCount(counter);
        return employer;
    }

    protected EmployerDtoById getEmployerDtoFromJson(String pathToJson) throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + pathToJson));
        return employerMapper.mapDataFromApiById(jsonString);
    }

    protected VacancyDto getVacancyDtoFromJson(String pathToJson) throws IOException {
        String jsonString = Files.readString(Path.of(JSON_BASE_PATH + pathToJson));
        return vacancyMapper.mapDataFromApiById(jsonString);
    }


}
