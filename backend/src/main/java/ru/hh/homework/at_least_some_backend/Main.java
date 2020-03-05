package ru.hh.homework.at_least_some_backend;

import ru.hh.nab.starter.NabApplication;

public class Main
{
    public static void main(String[] args)
    {
        NabApplication.builder()
                // .configureJersey(String.class).bindToRoot()
                .build().run(Config.class);
    }
}