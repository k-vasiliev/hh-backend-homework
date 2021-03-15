package ru.hh.school.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.hh.school.dto.VacancyDto;
import ru.hh.school.feignclient.HhApi;

public class VacancyService {

  private final HhApi api;

  private final ObjectMapper mapper;

  @Inject
  public VacancyService(HhApi api, ObjectMapper mapper) {
    this.api = api;
    this.mapper = mapper;
  }

  public List<VacancyDto> getVacancies(String query, Integer page, Integer perPage)
      throws JsonProcessingException, IOException {
    JsonNode vacancies = mapper.readTree(api.getVacancies(query, page, perPage)).get("items");
    return mapper.readerFor(new TypeReference<List<VacancyDto>>(){}).readValue(vacancies);
  }

  public VacancyDto getVacancy(Integer vacancyId) throws JsonProcessingException {
    return mapper.readValue(api.getVacancy(vacancyId), VacancyDto.class);
  }
}
