package ru.hh.backend;

import ru.hh.backend.config.AppConfig;
import ru.hh.nab.starter.NabApplication;

public class Application {

    public static void main(String[] args) {
        build().run(AppConfig.class);
    }

    private static NabApplication build() {
        return NabApplication.builder()
                .configureJersey().bindToRoot()
                .build();
    }
}
