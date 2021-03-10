package ru.hh.school.service;

import com.google.gson.Gson;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;
import org.eclipse.jetty.server.Response;
import ru.hh.school.dto.VacancyItemsApi;
import ru.hh.school.exception.HhRequestException;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Singleton
public class VacancyService {

    public VacancyItemsApi getVacancy(Integer page, Integer perPage, String query) throws HhRequestException {
        if (query == null) {
            throw new HhRequestException("query is empty");
        }

        final int defaultPage = 0;
        final int defaultPerPage = 20;
        page = page == null ? defaultPage : page;
        perPage = perPage == null ? defaultPerPage : perPage;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URIBuilder()
                            .setScheme("https")
                            .setHost("api.hh.ru")
                            .setPath("/vacancies")
                            .addParameter("text", query)
                            .addParameter("page", String.valueOf(page))
                            .addParameter("perPage", String.valueOf(perPage))
                            .build())
                    .timeout(Duration.ofSeconds(2))
                    .header(HttpHeaders.AUTHORIZATION,
                            "Bearer JOIN9M0LTBRLMF0S1JBLA2VUSFHAPSJF63PGT89P96D6HGNNHALD7QL2PSTKUD8P")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new HhRequestException(e.getMessage());
        }

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new HhRequestException(e.getMessage());
        }

        if (response.statusCode() != Response.SC_OK) {
            throw new HhRequestException("status code is "+ response.statusCode());
        }

        return new Gson().fromJson(response.body(), VacancyItemsApi.class);
    }
}
