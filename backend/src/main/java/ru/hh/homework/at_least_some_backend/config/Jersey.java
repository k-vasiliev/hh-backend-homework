package ru.hh.homework.at_least_some_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.homework.at_least_some_backend.resource.HelloWorldResource;

@Configuration
@Import(HelloWorldResource.class)
public class Jersey { }
