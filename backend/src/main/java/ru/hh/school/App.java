package ru.hh.school;

import ru.hh.nab.starter.NabApplication;

public class App {
    public static void main(String[] args) {
        NabApplication.builder()
            .configureJersey()
            .bindToRoot()
            .build()
            .run(Config.class);
    }
}
