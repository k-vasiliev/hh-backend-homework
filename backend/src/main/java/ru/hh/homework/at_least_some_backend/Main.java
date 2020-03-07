package ru.hh.homework.at_least_some_backend;

import ru.hh.nab.starter.NabApplication;

import ru.hh.homework.at_least_some_backend.config.General;
import ru.hh.homework.at_least_some_backend.config.Jersey;

public class Main
{
    public static void main(String[] args)
    {
        NabApplication.builder()
                .configureJersey(Jersey.class).bindToRoot()
                .build().run(General.class);
    }
}