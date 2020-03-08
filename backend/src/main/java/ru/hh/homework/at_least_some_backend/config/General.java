package ru.hh.homework.at_least_some_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.starter.NabProdConfig;
import ru.hh.nab.hibernate.NabHibernateProdConfig;

@Configuration
@Import({
        NabProdConfig.class,
        NabHibernateProdConfig.class,
        Hibernate.class
})
public class General { }
