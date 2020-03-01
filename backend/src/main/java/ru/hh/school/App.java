package ru.hh.school;

import ru.hh.nab.starter.NabApplication;
import ru.hh.school.config.ProdConfig;

public class App {

  public static void main(String[] args) {
    NabApplication
            .builder()
            .configureJersey()
            .bindToRoot()
            .build()
            .run(ProdConfig.class);
  }
}
