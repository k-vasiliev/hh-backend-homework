package ru.hh.school.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.VacancyDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Service
public class HhApiService {
    private final String BASE_API_URI = "https://api.hh.ru";

    private final ObjectMapper objectMapper;

    public HhApiService() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    private String getResponse(HttpRequest httpRequest) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private HttpRequest createHttpRequest(URI uri) {
        return HttpRequest
                .newBuilder()
                .uri(uri)
                .build();
    }

    public EmployerDto getEmployer(Integer id) throws IOException, InterruptedException {
        HttpRequest httpRequest = createHttpRequest(URI.create(BASE_API_URI + "/employers/" + id));
        String response = getResponse(httpRequest);

        return objectMapper.readerFor(EmployerDto.class).readValue(response);
    }

    public List<EmployerDto> getEmployers(String query, Integer page, Integer perPage) throws IOException, InterruptedException {
        URI uri = UriComponentsBuilder
                .fromUriString(BASE_API_URI + "/employers")
                .queryParam("text", query)
                .queryParam("page", page)
                .queryParam("per_page", perPage).build().toUri();

        HttpRequest httpRequest = createHttpRequest(uri);
        String response = getResponse(httpRequest);

        JsonNode jsonNode = objectMapper
                .readTree(response)
                .path("items");

        return Arrays.asList(objectMapper.readValue(jsonNode.toString(), EmployerDto[].class));
    }

    public VacancyDto getVacancy(Integer id) throws IOException, InterruptedException {
        HttpRequest httpRequest = createHttpRequest(URI.create(BASE_API_URI + "/vacancies/" + id));
        String response = getResponse(httpRequest);

        return objectMapper.readerFor(VacancyDto.class).readValue(response);
    }

    public List<VacancyDto> getVacancies(String query, Integer page, Integer perPage) throws IOException, InterruptedException {
        URI uri = UriComponentsBuilder
                .fromUriString(BASE_API_URI + "/vacancies")
                .queryParam("text", query)
                .queryParam("page", page)
                .queryParam("per_page", perPage).build().toUri();

        HttpRequest httpRequest = createHttpRequest(uri);
        String response = getResponse(httpRequest);

        JsonNode jsonNode = objectMapper
                .readTree(response)
                .path("items");

        return Arrays.asList(objectMapper.readValue(jsonNode.toString(), VacancyDto[].class));
    }
}
