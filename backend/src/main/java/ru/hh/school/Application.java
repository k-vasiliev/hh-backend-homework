package ru.hh.school;

import ru.hh.nab.starter.NabApplication;
import ru.hh.school.config.Config;
import ru.hh.school.config.JerseyConfig;

public class Application {

    public static void main(String[] args) {
        NabApplication.builder()
                .configureJersey(JerseyConfig.class)
                .bindToRoot()
                .build()
                .run(Config.class);
    }
}
