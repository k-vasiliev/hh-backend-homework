package ru.hh.school.config;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import feign.Feign;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.feignclient.HhApi;
import ru.hh.school.jackson.OffsetDateTimeDeserializer;
import ru.hh.school.jackson.OffsetDateTimeSerializer;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.resource.FavoriteEmployerResource;
import ru.hh.school.resource.VacancyResource;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.FavoriteEmployerService;
import ru.hh.school.service.VacancyService;

@Configuration
@Import({
  FavoriteEmployerService.class,
  FavoriteEmployerResource.class,
  VacancyService.class,
  VacancyResource.class,
  EmployerService.class,
  EmployerResource.class,
  NabCommonConfig.class
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }

  @Bean
  @Primary
  public ObjectMapper getObjectMapper() {
    JavaTimeModule timeModule = new JavaTimeModule();
    timeModule.addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer());
    timeModule.addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer());
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(timeModule);
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    return mapper;
  }

  @Bean
  public HhApi getHhApi() {
    return Feign.builder().target(HhApi.class, HhApi.API_URL);
  }
}
