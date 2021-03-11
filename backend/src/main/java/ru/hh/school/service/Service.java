package ru.hh.school.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import ru.hh.nab.common.properties.FileSettings;

public abstract class Service {

    private final static String SERVER = "https://api.hh.ru";

    private final RestTemplate rest;
    private final HttpHeaders headers;

    public Service(FileSettings fileSettings) {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");

        String bearerAuth = fileSettings.getString("bearerAuth");

        if (bearerAuth != null && !bearerAuth.isBlank()) {
            headers.setBearerAuth(bearerAuth);
        }
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
