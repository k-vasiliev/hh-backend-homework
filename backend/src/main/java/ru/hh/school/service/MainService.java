package ru.hh.school.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.EmployerDTO;
import ru.hh.school.dto.EmployersDTO;
import ru.hh.school.dto.VacanciesDTO;
import ru.hh.school.dto.VacancyDTO;

public class MainService {

    private final static String SERVER = "https://api.hh.ru";

    private static final String ME = "/me";

    private static final String EMPLOYERS = "/employers/";

    private static final String VACANCIES = "/vacancies/";

    private final RestTemplate rest;
    private final HttpHeaders headers;

    public MainService(FileSettings fileSettings) {
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

    public EmployersDTO findEmployers(Integer page, Integer perPage, String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getServer() + EMPLOYERS);

        if (page != null) {
            builder.queryParam("page", page);
        }

        if (perPage != null) {
            builder.queryParam("per_page", perPage);
        }

        builder.queryParam("text", query);

        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<EmployersDTO> responseEntity = getRest().exchange(builder.build().toUri(),
            HttpMethod.GET, requestEntity, EmployersDTO.class);

        return responseEntity.getBody();
    }

    public EmployerDTO getEmployer(Integer id) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<EmployerDTO> responseEntity = getRest().exchange(getServer() + EMPLOYERS + id,
            HttpMethod.GET, requestEntity, EmployerDTO.class);

        return responseEntity.getBody();
    }

    public VacanciesDTO findVacancies(Integer page, Integer perPage, String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getServer() + VACANCIES);

        if (page != null) {
            builder.queryParam("page", page);
        }

        if (perPage != null) {
            builder.queryParam("per_page", perPage);
        }

        builder.queryParam("text", query);

        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<VacanciesDTO> responseEntity = getRest().exchange(builder.build().toUri(),
            HttpMethod.GET, requestEntity, VacanciesDTO.class);

        return responseEntity.getBody();
    }

    public VacancyDTO getVacancy(Integer id) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<VacancyDTO> responseEntity = getRest().exchange(getServer() + VACANCIES + id,
            HttpMethod.GET, requestEntity, VacancyDTO.class);

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