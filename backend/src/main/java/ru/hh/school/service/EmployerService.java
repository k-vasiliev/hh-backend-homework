package ru.hh.school.service;

import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.EmployerDetailed;
import ru.hh.school.dto.Employers;

public class EmployerService extends Service {

    private static final String PATH = "/employers/";

    public EmployerService(FileSettings fileSettings) {
        super(fileSettings);
    }

    public Employers findEmployers(Integer page, Integer perPage, String query) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getServer() + PATH);

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

    public EmployerDetailed get(Integer id) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<EmployerDetailed> responseEntity = getRest().exchange(getServer() + PATH + id,
                HttpMethod.GET, requestEntity, EmployerDetailed.class);

        return responseEntity.getBody();
    }

}
