package ru.hh.back;

import ru.hh.back.config.BackConfig;
import ru.hh.back.config.BackJerseyConfig;
import ru.hh.nab.starter.NabApplication;

public class Startup {

  public static void main(String[] args) {
    NabApplication.builder()
            .configureJersey(BackJerseyConfig.class).bindToRoot()
            .build().run(BackConfig.class);
  }
}
