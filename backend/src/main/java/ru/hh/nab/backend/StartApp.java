package ru.hh.nab.backend;

import ru.hh.nab.starter.NabApplication;

public class StartApp {

  public static void main(String[] args) {
    NabApplication.builder()
      .configureJersey(JerseyConfig.class).bindToRoot()
      .build().run(AppConfig.class);
  }
}
