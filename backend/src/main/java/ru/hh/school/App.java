package ru.hh.school;

import ru.hh.nab.starter.NabApplication;
import ru.hh.school.config.ProdConfig;
import ru.hh.school.exceptionmapper.FeignExceptionMapper;
import ru.hh.school.exceptionmapper.GenericExceptionMapper;
import ru.hh.school.jackson.ObjectMapperContextResolver;

public class App {

  public static void main(String[] args) {
    NabApplication
            .builder()
            .configureJersey()
            .registerResources(FeignExceptionMapper.class, GenericExceptionMapper.class,
              ObjectMapperContextResolver.class)
            .bindToRoot()
            .build()
            .run(ProdConfig.class);
  }
}
