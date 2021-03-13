package ru.hh.school.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import ru.hh.school.entity.Vacancy;
import ru.hh.school.feignclient.HhApi;

@Service
public class VacancyService {

  private static HhApi api;

  private static ObjectMapper mapper;

  @Inject
  public VacancyService(HhApi hhApi, ObjectMapper objectMapper) {
    api = hhApi;
    mapper = objectMapper;
  }

  public List<Vacancy> getVacancies(String query, Integer page, Integer perPage)
      throws JsonProcessingException, IOException {
    JsonNode vacancies = mapper.readTree(api.getVacancies(query, page, perPage)).get("items");
    return mapper.readerFor(new TypeReference<List<Vacancy>>(){}).readValue(vacancies);
  }

  public Vacancy getVacancy(Integer vacancyId) throws JsonProcessingException {
    return mapper.readValue(api.getVacancy(vacancyId), Vacancy.class);
  }
}
