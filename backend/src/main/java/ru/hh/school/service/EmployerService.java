package ru.hh.school.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.hh.school.dto.EmployerDto;
import ru.hh.school.feignclient.HhApi;

public class EmployerService {

  private static HhApi api;

  private static ObjectMapper mapper;

  @Inject
  public EmployerService(HhApi hhApi, ObjectMapper objectMapper) {
    api = hhApi;
    mapper = objectMapper;
  }

  public List<EmployerDto> getEmployers(String query, Integer page, Integer perPage)
      throws JsonProcessingException, IOException {
    JsonNode employers = mapper.readTree(api.getEmployers(query, page, perPage)).get("items");
    return mapper.readerFor(new TypeReference<List<EmployerDto>>(){}).readValue(employers);
  }

  public EmployerDto getEmployer(Integer employerId) throws JsonProcessingException {
    return mapper.readValue(api.getEmployer(employerId), EmployerDto.class);
  }
}
