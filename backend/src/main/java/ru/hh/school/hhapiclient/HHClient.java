package ru.hh.school.hhapiclient;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.IOException;
import java.lang.InterruptedException;

import javax.inject.Singleton;

@Singleton
public class HHClient {

    private HttpClient client;

    private final String BASE_HH_URI = "https://api.hh.ru";

    private ObjectMapper mapper;

    public HHClient() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.findAndRegisterModules();
    }

    private HttpRequest buildRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
    }

    private String getResponse(HttpRequest request) throws RuntimeException, IOException, InterruptedException {
        HttpResponse<String> response =
                client.send(request, BodyHandlers.ofString());
        if (response.statusCode() > 300) {
            throw new RuntimeException("Error http status " + response.statusCode() + ", body is " + response.body());
        }

        return response.body();
    }

    public List<Vacancy> getVacancies(String query, int page, int per_page) throws RuntimeException, JsonProcessingException, IOException, InterruptedException {
        String uri = BASE_HH_URI + "/vacancies" + "?query=" + query + "&page=" + page + "&per_page=" + per_page;
        HttpRequest req = buildRequest(uri);
        String resp = getResponse(req);
        NestedVacancies vacancies = mapper.readValue(resp, new TypeReference<NestedVacancies>() { });
        return vacancies.items;
    }

    public Vacancy getVacancy(String id) throws RuntimeException, JsonProcessingException, IOException, InterruptedException {
        HttpRequest req = buildRequest(BASE_HH_URI + "/vacancies" + "/" + id);
        String resp = getResponse(req);
        Vacancy vacancy = mapper.readValue(resp, Vacancy.class);
        return vacancy;
    }

    public List<Employer> getEmployers(String query, int page, int per_page) throws RuntimeException, JsonProcessingException, IOException, InterruptedException {
        String uri = BASE_HH_URI + "/employers" + "?query=" + query + "&page=" + page + "&per_page=" + per_page;
        HttpRequest req = buildRequest(uri);
        String resp = getResponse(req);
        NestedEmployers employers = mapper.readValue(resp, new TypeReference<NestedEmployers>() { });
        return employers.items;
    }

    public Employer getEmployer(String id) throws RuntimeException, JsonProcessingException, IOException, InterruptedException {
        HttpRequest req = buildRequest(BASE_HH_URI + "/employers" + "/" + id);
        String resp = getResponse(req);
        Employer employer = mapper.readValue(resp, Employer.class);
        return employer;
    }

}
