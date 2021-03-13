package ru.hh.school.service;

import com.google.gson.Gson;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;
import org.eclipse.jetty.server.Response;
import ru.hh.school.dto.EmployerApiHh;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.dto.EmployerItemsApiHh;
import ru.hh.school.dto.VacancyItemsApiHh;
import ru.hh.school.exception.HhRequestException;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Singleton
public class ApiHhService {

    // TODO можно вынести в конфиг (на лекции по API токен разрешали светить в паблик репах)
    private static final String TOKEN = "Bearer JOIN9M0LTBRLMF0S1JBLA2VUSFHAPSJF63PGT89P96D6HGNNHALD7QL2PSTKUD8P";

    private final String employersUrl = "https://api.hh.ru/employers/";

    private final String vacanciesUrl = "https://api.hh.ru/vacancies/";

    private HttpResponse<String> getHttpResponse(String url, String query, Integer page, Integer perPage) throws HhRequestException {
        final int defaultPage = 0;
        final int defaultPerPage = 20;
        page = page == null ? defaultPage : page;
        perPage = perPage == null ? defaultPerPage : perPage;

        URI uri = null;
        try {
            uri = new URIBuilder(url)
                    .addParameter("text", query)
                    .addParameter("page", String.valueOf(page))
                    .addParameter("per_page", String.valueOf(perPage))
                    .build();
        } catch (URISyntaxException e) {
            throw new HhRequestException(e.getMessage());
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.ofSeconds(2))
                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new HhRequestException(e.getMessage());
        }

        if (response.statusCode() != Response.SC_OK) {
            throw new HhRequestException("status code is " + response.statusCode());
        }

        return response;
    }

    public VacancyItemsApiHh getVacancies(String query, Integer page, Integer perPage) throws HhRequestException {
        HttpResponse<String> response = getHttpResponse(vacanciesUrl, query, page, perPage);
        return new Gson().fromJson(response.body(), VacancyItemsApiHh.class);
    }

    public VacancyDto getVacancyBy(Integer id) throws HhRequestException {
        HttpResponse<String> response = getHttpResponse(String.format("%s%d", vacanciesUrl, id), null, null, null);
        return new Gson().fromJson(response.body(), VacancyDto.class);
    }

    public EmployerItemsApiHh getEmployers(String query, Integer page, Integer perPage) throws HhRequestException {
        HttpResponse<String> response = getHttpResponse(employersUrl, query, page, perPage);
        return new Gson().fromJson(response.body(), EmployerItemsApiHh.class);
    }

    public EmployerApiHh getEmployerBy(Integer id) throws HhRequestException {
        HttpResponse<String> response = getHttpResponse(String.format("%s%d", employersUrl, id), null, null, null);
        return new Gson().fromJson(response.body(), EmployerApiHh.class);
    }

}
