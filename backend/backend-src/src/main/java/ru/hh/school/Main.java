package ru.hh.school;

import ru.hh.nab.starter.NabApplication;
import ru.hh.school.config.ProdConfig;


/**
 * Main server
 *
 */
public class Main
{
    public static NabApplication  createApp() {
        return NabApplication
                .builder()
                .configureJersey()
                .bindToRoot()
                .build();

    }

    public static void main(String [] args) {
        createApp().run(ProdConfig.class);
    }
}
