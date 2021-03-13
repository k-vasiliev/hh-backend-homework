package ru.hh.school.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import ru.hh.school.entity.Employer;
import ru.hh.school.feignclient.HhApi;

@Service
public class EmployerService {

  private static HhApi api;

  private static ObjectMapper mapper;

  @Inject
  public EmployerService(HhApi hhApi, ObjectMapper objectMapper) {
    api = hhApi;
    mapper = objectMapper;
  }

  public List<Employer> getEmployers(String query, Integer page, Integer perPage)
      throws JsonProcessingException, IOException {
    JsonNode employers = mapper.readTree(api.getEmployers(query, page, perPage)).get("items");
    return mapper.readerFor(new TypeReference<List<Employer>>(){}).readValue(employers);
  }

  public Employer getEmployer(Integer employerId) throws JsonProcessingException {
    return mapper.readValue(api.getEmployer(employerId), Employer.class);
  }
}
