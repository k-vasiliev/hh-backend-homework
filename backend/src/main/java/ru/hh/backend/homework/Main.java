package ru.hh.backend.homework;

import ru.hh.backend.homework.config.AppConfig;
import ru.hh.backend.homework.config.JerseyConfig;
import ru.hh.nab.starter.NabApplication;

public class Main {
    public static void main(String[] args) {
        NabApplication
                .builder()
                .configureJersey(JerseyConfig.class)
                .bindToRoot()
                .build()
                .run(AppConfig.class);
    }
}
