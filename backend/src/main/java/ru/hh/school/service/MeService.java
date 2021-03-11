package ru.hh.school.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hh.nab.common.properties.FileSettings;

public class MeService extends Service {

    private static final String PATH = "/me";

    public MeService(FileSettings fileSettings) {
        super(fileSettings);
    }

    public String getMe() {
        HttpEntity<String> requestEntity = new HttpEntity<>("", getHeaders());
        ResponseEntity<String> responseEntity = getRest().exchange(getServer() + PATH, HttpMethod.GET,
                requestEntity, String.class);

        return responseEntity.getBody();
    }

}
