package ru.hh.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hh.school.resource.dto.HHEmployerResponseDto;
import ru.hh.school.resource.dto.HHEmployersResponseDto;
import ru.hh.school.service.EmployerService;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Service
public class EmployerServiceImpl implements EmployerService {
    private static final Logger logger = LoggerFactory.getLogger(EmployerServiceImpl.class);
    public static final String HH_EMPLOYERS_BASE_URL = "https://api.hh.ru/employers";

    private final RestTemplate restTemplate;

    @Autowired
    public EmployerServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Получение компаний от HH API
     */
    @Override
    public HHEmployersResponseDto getHHEmployers(String query, Integer page, Integer perPage) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("page", page.toString());
        urlParams.put("per_page", perPage.toString());
        if (query != null) {
            urlParams.put("text", query);
        }

        HttpEntity<HHEmployersResponseDto> response = restTemplate.getForEntity(
                HH_EMPLOYERS_BASE_URL,
                HHEmployersResponseDto.class,
                urlParams
        );
        return response.getBody();
    }

    /**
     * Получение компаний от HH API
     */
    @Override
    public HHEmployerResponseDto getHHEmployerById(Long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<HHEmployerResponseDto> response = restTemplate.getForEntity(
                    HH_EMPLOYERS_BASE_URL + "/" + id,
                    HHEmployerResponseDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}