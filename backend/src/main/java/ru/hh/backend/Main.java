package ru.hh.backend;

import ru.hh.backend.config.ApplicationConfig;
import ru.hh.backend.config.JerseyConfig;
import ru.hh.nab.starter.NabApplication;

public class Main {
    public static void main(String[] args) {
        NabApplication.builder()
                .configureJersey(JerseyConfig.class).bindToRoot()
                .build().run(ApplicationConfig.class);
    }
}
