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
import ru.hh.school.resource.dto.HHVacanciesResponseDto;
import ru.hh.school.resource.dto.HHVacancyResponseDto;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.VacancyService;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Service
public class VacancyServiceImpl implements VacancyService {
    private static final Logger logger = LoggerFactory.getLogger(VacancyServiceImpl.class);
    public static final String HH_VACANCIES_BASE_URL = "https://api.hh.ru/vacancies";

    private final RestTemplate restTemplate;

    @Autowired
    public VacancyServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Получение компаний от HH API
     */
    @Override
    public HHVacanciesResponseDto getHHVacancies(String query, Integer page, Integer perPage) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("page", page.toString());
        urlParams.put("per_page", perPage.toString());
        if (query != null) {
            urlParams.put("text", query);
        }

        HttpEntity<HHVacanciesResponseDto> response = restTemplate.getForEntity(
                HH_VACANCIES_BASE_URL,
                HHVacanciesResponseDto.class,
                urlParams
        );
        return response.getBody();
    }

    /**
     * Получение компаний от HH API
     */
    @Override
    public HHVacancyResponseDto getHHVacancyById(Long vacancyId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<HHVacancyResponseDto> response = restTemplate.getForEntity(
                HH_VACANCIES_BASE_URL + "/" + vacancyId,
                HHVacancyResponseDto.class
        );
        return response.getBody();
    }
}