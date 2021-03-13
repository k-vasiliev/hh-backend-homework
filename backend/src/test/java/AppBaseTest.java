import org.hibernate.SessionFactory;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AppBaseTest extends NabTestBase {

    protected final String EMPLOYER_BASE_URL = "/employer";
    protected final String VACANCY_BASE_URL = "/vacancy";
    protected final String FAVORITE_EMPLOYER_BASE_URL= "/favorites/employer";
    protected final String FAVORITE_VACANCY_BASE_URL= "/favorites/vacancy";

    protected final int vacancyId = 42859569;
    protected final int employerId = 4849846;

    @Inject
    protected SessionFactory sessionFactory;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Override
    protected NabApplication getApplication() {
        return NabApplication.builder().configureJersey().bindToRoot().build();
    }

    protected HttpResponse<String> executePostRequestWithParams(String url, Map<String,String> params) {
        HttpRequest request = createPostRequestWithParameters(url, params);
        return executeRequest(request);
    }

    protected HttpResponse<String> executePutRequestWithParams(String url, Map<String,String> params) {
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
        } catch (URISyntaxException e) { return null; }
    }

    private HttpRequest createDeleteRequest(String url) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(baseUrl() + url))
                    .DELETE().build();
        } catch (URISyntaxException e) { return null; }
    }

    private HttpRequest createPostRequestWithParameters(String url, Map<String,String> params) {
        try {
            String requestParams = params.keySet().stream()
                    .map(key -> key + "=" + params.get(key))
                    .collect(Collectors.joining("&"));
            return HttpRequest.newBuilder()
                    .uri(new URI(baseUrl() + url))
                    .headers("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestParams))
                    .build();
        } catch (URISyntaxException e) { return null; }
    }

    private HttpRequest createPutRequestWithParameters(String url, Map<String,String> params) {
        try {
            String requestParams = params.keySet().stream()
                    .map(key -> key + "=" + params.get(key))
                    .collect(Collectors.joining("&"));
            return HttpRequest.newBuilder()
                    .uri(new URI(baseUrl() + url))
                    .headers("Content-Type", "application/x-www-form-urlencoded")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestParams))
                    .build();
        } catch (URISyntaxException e) { return null; }
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

}
