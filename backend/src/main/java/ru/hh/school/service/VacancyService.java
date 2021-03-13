package ru.hh.school.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.Vacancies;
import ru.hh.school.dto.Vacancy;

public class VacancyService extends Service {

    private static final String PATH = "/vacancies/";

    public VacancyService(FileSettings fileSettings) {
        super(fileSettings);
    }

    public Vacancies findVacancies(Integer page, Integer perPage, String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getServer() + PATH);

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

    public Vacancy get(Integer id) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<Vacancy> responseEntity = getRest().exchange(getServer() + PATH + id,
                HttpMethod.GET, requestEntity, Vacancy.class);

        return responseEntity.getBody();
    }

}
