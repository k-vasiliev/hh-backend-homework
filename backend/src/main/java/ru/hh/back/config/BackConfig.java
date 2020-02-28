package ru.hh.back.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;

@Configuration
@Import({NabProdConfig.class,
        NabHibernateProdConfig.class,
        BackCommonConfig.class})
@ComponentScan("ru.hh.backend")
public class BackConfig {
}
