package ru.hh.school.service;

import com.google.gson.Gson;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.HttpHeaders;
import ru.hh.school.dto.EmployerItems;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public final class EmployerService {

    public static EmployerItems getEmployers(String page, String per_page, String query) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URIBuilder()
                        .setScheme("https")
                        .setHost("api.hh.ru")
                        .setPath("/employers")
                        .addParameter("text", query)
                        .addParameter("page", String.valueOf(page))
                        .addParameter("per_page", String.valueOf(per_page))
                        .build())
                .timeout(Duration.ofSeconds(10))
                .header(HttpHeaders.AUTHORIZATION,
                        "Bearer JOIN9M0LTBRLMF0S1JBLA2VUSFHAPSJF63PGT89P96D6HGNNHALD7QL2PSTKUD8P")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), EmployerItems.class);
    }
}
