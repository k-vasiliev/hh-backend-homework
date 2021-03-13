package ru.hh.school.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import feign.Feign;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.feignclient.HhApi;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.service.EmployerService;

@Configuration
@Import({
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
    ObjectMapper mapper = new ObjectMapper();
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
