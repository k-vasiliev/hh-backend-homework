package ru.hh.school;

import ru.hh.nab.starter.NabApplication;
import ru.hh.school.config.JerseyConfig;
import ru.hh.school.config.ProdConfig;

public class App {

    public static void main(String[] args) {
        System.setProperty("settingsDir", "src/etc");
        NabApplication
                .builder()
                .configureJersey(JerseyConfig.class)
                .bindToRoot()
                .build()
                .run(ProdConfig.class);
    }
}
