package ru.hh.school.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.EmployerDetailed;
import ru.hh.school.dto.Employers;
import ru.hh.school.dto.Vacancies;
import ru.hh.school.dto.Vacancy;

public class HhService {

    private final static String SERVER = "https://api.hh.ru";

    private static final String ME = "/me";

    private static final String EMPLOYERS = "/employers/";

    private static final String VACANCIES = "/vacancies/";

    private final RestTemplate rest;
    private final HttpHeaders headers;

    public HhService(FileSettings fileSettings) {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");

        String bearerAuth = fileSettings.getString("bearerAuth");

        if (bearerAuth != null && !bearerAuth.isBlank()) {
            headers.setBearerAuth(bearerAuth);
        }
    }

    public String getMe() {
        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<String> responseEntity = getRest().exchange(getServer() + ME, HttpMethod.GET,
                requestEntity, String.class);

        return responseEntity.getBody();
    }

    public Employers findEmployers(Integer page, Integer perPage, String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getServer() + EMPLOYERS);

        if (page != null) {
            builder.queryParam("page", page);
        }

        if (perPage != null) {
            builder.queryParam("per_page", perPage);
        }

        builder.queryParam("text", query);

        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<Employers> responseEntity = getRest().exchange(builder.build().toUri(),
                HttpMethod.GET, requestEntity, Employers.class);

        return responseEntity.getBody();
    }

    public EmployerDetailed getEmployer(Integer id) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<EmployerDetailed> responseEntity = getRest().exchange(getServer() + EMPLOYERS + id,
                HttpMethod.GET, requestEntity, EmployerDetailed.class);

        return responseEntity.getBody();
    }

    public Vacancies findVacancies(Integer page, Integer perPage, String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getServer() + VACANCIES);

        if (page != null) {
            builder.queryParam("page", page);
        }

        if (perPage != null) {
            builder.queryParam("per_page", perPage);
        }

        builder.queryParam("text", query);

        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<Vacancies> responseEntity = getRest().exchange(builder.build().toUri(),
                HttpMethod.GET, requestEntity, Vacancies.class);

        return responseEntity.getBody();
    }

    public Vacancy getVacancy(Integer id) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<Vacancy> responseEntity = getRest().exchange(getServer() + VACANCIES + id,
                HttpMethod.GET, requestEntity, Vacancy.class);

        return responseEntity.getBody();
    }

    protected String getServer() {
        return SERVER;
    }

    protected RestTemplate getRest() {
        return rest;
    }

    protected HttpHeaders getHeaders() {
        return headers;
    }

}
