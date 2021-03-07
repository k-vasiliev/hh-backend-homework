package ru.hh.school;

import ru.hh.nab.starter.NabApplication;
import ru.hh.school.config.ProdConfig;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployerItems;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.service.EmployerService;

import java.io.IOException;
import java.net.URISyntaxException;

public class App {

  public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
    NabApplication
            .builder()
            .configureJersey()
            .bindToRoot()
            .build()
            .run(ProdConfig.class);
  }
}
