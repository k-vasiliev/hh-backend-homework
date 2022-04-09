package ru.hh.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hh.school.resource.dto.AreaData;
import ru.hh.school.service.AreaService;

import javax.inject.Singleton;

@Singleton
@Service
public class AreaServiceImpl implements AreaService {
    private static final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
    public static final String HH_AREA_BASE_URL = "https://api.hh.ru/areas";

    private final RestTemplate restTemplate;

    @Autowired
    public AreaServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Получение компаний от HH API
     */
    @Override
    public AreaData getAreaData(Long areaId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<AreaData> response = restTemplate.getForEntity(
                    HH_AREA_BASE_URL + "/" + areaId,
                    AreaData.class
            );
            return response.getBody();
        } catch (Exception e) {
            logger.error("Area data {} not found", areaId);
            return null;
        }
    }
}